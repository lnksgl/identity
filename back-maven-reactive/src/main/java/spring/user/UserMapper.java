package spring.user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import reactor.core.publisher.Mono;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(Mono<User> user);

    User dtoToUser(UserDto userDto);
}
