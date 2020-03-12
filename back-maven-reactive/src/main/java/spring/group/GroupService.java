package spring.group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class GroupService {

    GroupRepository groupRepository;
    GroupMapper groupMapper;

    public void createGroup(GroupDto groupDto) {
        groupRepository.save(mapFromDtoToGroup(groupDto)).subscribe();
    }

    public void updateGroup(GroupDto groupDto) {
        groupRepository.updateGroup(groupDto.getName(), groupDto.getContent(), groupDto.getId());
    }

    public void updateAverage(double average, Long id) {
        groupRepository.updateGroup(String.valueOf(average), id);
    }

    public Flux<GroupDto> showAllGroups() {
        return groupRepository.findAll().map(this::mapFromGroupToGroupDto);
    }

    public void deleteGroup(long id) {
        groupRepository.deleteById(id).subscribe();
    }

    public Mono<GroupDto> readSingleGroup(Long id) {
        return justGroupDto(groupRepository.findById(id));
    }

    public Mono<Group> readNameGroup(String name) {
        return groupRepository.findByName(name);
    }

    public Mono<GroupDto> showNameGroup(String name) {
        return justGroupDto(readNameGroup(name));
    }

    private Mono<GroupDto> justGroupDto(Mono<Group> group) {
        return Mono.just(mapFromMonoGroupToDto(group));
    }

    public GroupDto mapFromMonoGroupToDto(Mono<Group> group) {
        return groupMapper.monoGroupToGroupDto(group);
    }

    private Group mapFromDtoToGroup(GroupDto group) {
        return groupMapper.dtoToGroup(group);
    }

    private GroupDto mapFromGroupToGroupDto(Group group) {
        return groupMapper.groupToGroupDto(group);
    }
}
