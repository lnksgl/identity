package spring.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-04T16:54:51+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 9 (Oracle Corporation)"
)
@Component
public class UserAverageMapperImpl implements UserAverageMapper {

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
    public User dtoToUser(UserAverageDto userAverageDto) {
        if ( userAverageDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userAverageDto.getId() );
        user.setUsername( userAverageDto.getUsername() );
        user.setEmail( userAverageDto.getEmail() );

        return user;
    }
}
