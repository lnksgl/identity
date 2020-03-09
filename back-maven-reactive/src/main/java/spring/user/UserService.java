package spring.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import spring.enquiry.EnquiryMathOperations;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    UserAverageMapper userAverageMapper;
    EnquiryMathOperations enquiryMathOperations;

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
        //or
        userRepository.create(user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Cacheable
    public Flux<UserAverageDto> showAllUsers() {
        return userRepository.findAll().map(this::mapFromUserToAverageDto);
    }

    @Transactional
    public void updateUser(UserAverageDto userAverageDto) {
        userRepository.updateUser(avg(userAverageDto.getAverage()), userAverageDto.getId());
    }

    public String avg(String evaluations) {
        return String.valueOf(
                enquiryMathOperations.average(evaluations.replaceAll("[^\\d.]", "").split("")));
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Cacheable
    public Mono<UserAverageDto> readCurrentUser(String username) {
        return justAverageDto(userRepository.findByUsername(username));
    }

    @Cacheable
    public Mono<UserDto> showUsername(String username) {
        return justUserDto(userRepository.findByUsername(username));
    }

    @Cacheable
    public Mono<User> showEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkUsername(String username, String email) {
        return true;//showUsername(username) == null && showEmail(email) == null;
    }

    @Cacheable
    public Mono<UserDto> readSingleUser(Long id) {
        return justUserDto(userRepository.findById(id));
    }

    private Mono<UserDto> justUserDto(Mono<User> user) {
        return Mono.just(mapFromUserToDto(user));
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
}
