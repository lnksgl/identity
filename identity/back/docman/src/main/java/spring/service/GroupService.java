package spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.GroupDto;
import spring.exception.PostNotFoundException;
import spring.mapper.GroupMapper;
import spring.model.Group;
import spring.repository.GroupRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupDto groupDto) {
        groupRepository.save(mapFromDtoToGroup(groupDto));
    }

    @Transactional
    public void updateGroup(GroupDto groupDto) {
        groupRepository.updateGroup(groupDto.getName(), groupDto.getContent(), groupDto.getId());
    }

    @Cacheable
    public List<GroupDto> showAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groupsStream(groups);
    }

    @Transactional
    public void deleteGroup(long id) {
        groupRepository.delete(groupRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    @Cacheable
    public GroupDto readSingleGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromGroupToDto(group);
    }

    @Cacheable
    public List<GroupDto> showNameGroup(String name) {
        return groupsStream(groupRepository.findByName(name));
    }

    public List<GroupDto> groupsStream(List<Group> groups) {
        return groups.stream().map(this::mapFromGroupToDto).collect(toList());
    }

    public GroupDto mapFromGroupToDto(Group group) {
        return groupMapper.groupToGroupDto(group);
    }

    public Group mapFromDtoToGroup(GroupDto group) {
        return groupMapper.dtoToGroup(group);
    }
}
