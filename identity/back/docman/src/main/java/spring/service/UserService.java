package spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.GroupDto;
import spring.dto.UserAverageDto;
import spring.dto.UserDto;
import spring.dto.UserFileDto;
import spring.exception.PostNotFoundException;
import spring.mapper.UserMapper;
import spring.model.User;
import spring.repository.UserRepository;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class UserService {

    private static final Logger LOG = Logger.getLogger(AuthService.class.getName());

    UserRepository userRepository;
    UserMapper userMapper;

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Cacheable
    public List<UserDto> showAllUsers() {
        List<User> users = userRepository.findAll();
        LOG.log(Level.INFO, "show users success");
        return usersStream(users);
    }

    @Transactional
    public void updateUser(UserAverageDto userAverageDto) {
        userRepository.updateUser(avg(userAverageDto.getAverage()), userAverageDto.getId());
    }

    public String avg(String average) {
        String[] strMas = average.replaceAll("[^\\d.]", "").split("");
        int sum = 0;

        for (int i = 0; i < strMas.length; i++) {
            sum += Integer.decode(strMas[i]);
        }

        return String.valueOf(sum / strMas.length);
    }

    @Transactional
    public void deleteUser(long id) {
       userRepository.delete(userRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
        LOG.log(Level.INFO, "delete user success");
    }

    @Cacheable
    public UserDto readSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        LOG.log(Level.INFO, "read single user success");
        return mapFromUserToDto(user);
    }

    @Cacheable
    public UserAverageDto readCurrentUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new PostNotFoundException("For id " + username));
        return mapFromUserToAverageDto(user);
    }

    public boolean checkUsername(String username, String email) {
        return showUsername(username).isEmpty() && showEmail(email).isEmpty();
    }

    @Cacheable
    public List<UserDto> showUsername(String username) {
        LOG.log(Level.INFO, "show user-username success");
        return usersStream(userRepository.findByUsername(username).stream().collect(Collectors.toList()));
    }

    @Cacheable
    public List<User> showEmail(String email) {
        LOG.log(Level.INFO, "show user-email success");
        return userRepository.findByEmail(email);
    }

    public List<UserDto> usersStream(List<User> users) {
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    public UserDto mapFromUserToDto(User user) {
        return userMapper.userToUserDto(user);
    }

    public User mapFromDtoToUser(UserDto user) {
        return userMapper.dtoToUser(user);
    }

    public UserAverageDto mapFromUserToAverageDto(User user) {
        UserAverageDto userAverageDto = new UserAverageDto();
        userAverageDto.setId(user.getId());
        userAverageDto.setUsername(user.getUsername());
        userAverageDto.setEmail(user.getEmail());
        userAverageDto.setAverage(user.getScanDocument());

        return userAverageDto;
    }
}
