package spring.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.enquiry.EnquiryMathOperations;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    UserAverageMapper userAverageMapper;
    EnquiryMathOperations enquiryMathOperations;

    public void createUser(User user) {
        userRepository.save(user).subscribe();
    }

    public Flux<UserAverageDto> showAllUsers() {
        return userRepository.findAll().map(this::mapFromUserToAverageDto);
    }

    public void updateUser(UserAverageDto userAverageDto) {
        userRepository.updateUser(avg(userAverageDto.getAverage()), userAverageDto.getId());
    }

    public String avg(String evaluations) {
        return String.valueOf(
                enquiryMathOperations.average(evaluations.replaceAll("[^\\d.]", "").split("")));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id).subscribe();
    }

    public Mono<UserAverageDto> readCurrentUser(String username) {
        return justUserDto(userRepository.findByUsername(username));
    }

    public Mono<UserAverageDto> showUsername(String username) {
        return justUserDto(userRepository.findByUsername(username));
    }

    public Mono<User> showEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkUsername(String username, String email) {
        return true;
        //showUsername(username) == null && showEmail(email) == null;
    }

    public Mono<UserAverageDto> readSingleUser(Long id) {
        return justUserDto(userRepository.findById(id));
    }

    private Mono<UserAverageDto> justUserDto(Mono<User> user) {
        return user.map(this::mapFromUserToAverageDto);
    }

    private Mono<UserAverageDto> justAverageDto(Mono<User> user) {
        return Mono.just(mapFromMonoUserToAverageDto(user));
    }

    private UserDto mapFromUserToDto(Mono<User> user) {
        return userMapper.userToUserDto(user);
    }

    private UserAverageDto mapFromMonoUserToAverageDto(Mono<User> user) {
        return userAverageMapper.monoUserToUserDto(user);
    }

    private UserAverageDto mapFromUserToAverageDto(User user) {
        return userAverageMapper.userToUserDto(user);
    }

    public User mapFromMonoUserAverageToUser(Mono<UserAverageDto> user) {
        return userAverageMapper.monoUserAverageToUser(user);
    }

    public User mapFromMonoUserToUser(Mono<User> user) {
        return userMapper.monoUserToUser(user);
    }
}
