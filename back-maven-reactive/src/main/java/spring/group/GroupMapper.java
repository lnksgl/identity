package spring.group;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface GroupMapper  {

    GroupDto groupToGroupDto(Mono<Group> group);

    Group dtoToGroup(GroupDto groupDto);
}
