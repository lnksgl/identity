package spring.mapper;

import org.mapstruct.Mapper;
import spring.dto.UserAverageDto;
import spring.model.User;

@Mapper(componentModel = "spring")
public interface UserAverageMapper {

    UserAverageDto userToUserDto(User user);

    User dtoToUser(UserAverageDto userAverageDto);
}
