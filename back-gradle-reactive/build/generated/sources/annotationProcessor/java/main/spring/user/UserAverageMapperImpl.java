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
public class UserAverageMapperImpl implements UserAverageMapper {

    @Override
    public UserAverageDto monoUserToUserDto(Mono<User> user) {
        if ( user == null ) {
            return null;
        }

        UserAverageDto userAverageDto = new UserAverageDto();

        return userAverageDto;
    }

    @Override
    public UserAverageDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserAverageDto userAverageDto = new UserAverageDto();

        userAverageDto.setId( user.getId() );
        userAverageDto.setUsername( user.getUsername() );
        userAverageDto.setEmail( user.getEmail() );

        return userAverageDto;
    }

    @Override
    public User dtoToUser(UserAverageDto user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );
        user1.setUsername( user.getUsername() );
        user1.setEmail( user.getEmail() );

        return user1;
    }

    @Override
    public User monoUserAverageToUser(Mono<UserAverageDto> user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        return user1;
    }
}
