package spring.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-12T14:09:33+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(Mono<User> user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public User monoUserToUser(Mono<User> user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        return user1;
    }

    @Override
    public User dtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setEmail( userDto.getEmail() );

        return user;
    }
}
