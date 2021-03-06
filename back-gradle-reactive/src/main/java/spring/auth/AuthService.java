package spring.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import spring.jwt.JwtProvider;
import spring.user.User;
import spring.user.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CacheConfig(cacheNames = {"service"})
public class AuthService {

    UserService userService;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;

    public void signUp(AuthRegisterRequest authRegisterRequest) {
        if (userService.checkUsername(authRegisterRequest.getUsername(), authRegisterRequest.getEmail())) {
            User user = new User();
            user.setUsername(authRegisterRequest.getUsername());
            user.setPassword(encodePassword(authRegisterRequest.getPassword()));
            user.setEmail(authRegisterRequest.getEmail());
            userService.createUser(user);
        } else {
            System.out.println("false");
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Cacheable
    public Mono<AuthResponse> login(AuthLoginRequest authLoginRequest) {
        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getUsername(),
                authLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken();
        return Mono.just(new AuthResponse(authenticationToken, authLoginRequest.getUsername()));
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return Optional.of(principal);
    }
}
