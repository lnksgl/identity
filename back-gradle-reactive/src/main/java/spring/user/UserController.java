package spring.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.Flow;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @GetMapping
    public Flux<UserAverageDto> showAllUsers() {
        return userService.showAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public Mono<UserAverageDto> getSingleUser(@PathVariable Long id) {
        return userService.readSingleUser(id);
    }

    @GetMapping("/username/{username}")
    public Mono<UserAverageDto> getUsername(@PathVariable String username) {
        return userService.showUsername(username);
    }

    @GetMapping("/current-username/{username}")
    public Mono<UserAverageDto> getCurrentUser(@PathVariable String username) {
        return userService.readCurrentUser(username);
    }

    @PutMapping
    public void updateUser(@RequestBody UserAverageDto userAverageDto)  {
        userService.updateUser(userAverageDto);
    }
}
