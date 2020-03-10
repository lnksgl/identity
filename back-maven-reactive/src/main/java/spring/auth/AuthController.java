package spring.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody AuthRegisterRequest authRegisterRequest) {
        authService.signUp(authRegisterRequest);
    }

    @GetMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest authLoginRequest) {
        return authService.login(authLoginRequest).block();
    }
}
