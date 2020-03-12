package spring.group;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface GroupMapper  {

    GroupDto monoGroupToGroupDto(Mono<Group> group);

    GroupDto groupToGroupDto(Group group);

    Group dtoToGroup(GroupDto groupDto);
}
