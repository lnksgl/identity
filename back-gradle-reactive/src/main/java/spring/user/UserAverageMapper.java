package spring.user;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface UserAverageMapper {

    UserAverageDto monoUserToUserDto(Mono<User> user);

    UserAverageDto userToUserDto(User user);

    User dtoToUser(UserAverageDto user);

    User monoUserAverageToUser(Mono<UserAverageDto> user);
}
