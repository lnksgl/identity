package spring.group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoOperator;
import spring.user.User;
import spring.user.UserDto;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class GroupService {

    GroupRepository groupRepository;
    GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupDto groupDto) {
        groupRepository.save(mapFromDtoToGroup(groupDto));
    }

    @Transactional
    public void updateGroup(GroupDto groupDto) {
        groupRepository.updateGroup(groupDto.getName(), groupDto.getContent(), groupDto.getId());
    }

    @Transactional
    public void updateAverage(double average, Long id) {
        groupRepository.updateGroup(String.valueOf(average), id);
    }

    @Cacheable
    public Flux<GroupDto> showAllGroups() {
        return null;//groupRepository.findAll().map(this::mapFromGroupToDto);
    }

    @Transactional
    public void deleteGroup(long id) {
        groupRepository.deleteById(id);
    }

    @Cacheable
    public Mono<GroupDto> readSingleGroup(Long id) {
        return justGroupDto(groupRepository.findById(id));
    }

    @Cacheable
    public Mono<GroupDto> showNameGroup(String name) {
        return justGroupDto(groupRepository.findByName(name));
    }

    private Mono<GroupDto> justGroupDto(Mono<Group> group) {
        return Mono.just(mapFromGroupToDto(group));
    }

    private GroupDto mapFromGroupToDto(Mono<Group> group) {
        return groupMapper.groupToGroupDto(group);
    }

    private Group mapFromDtoToGroup(GroupDto group) {
        return groupMapper.dtoToGroup(group);
    }
}
