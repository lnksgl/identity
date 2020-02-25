package spring.mapper;

import org.mapstruct.Mapper;
import spring.dto.GroupDto;
import spring.model.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper  {

    GroupDto groupToGroupDto(Group group);

    Group dtoToGroup(GroupDto groupDto);
}
