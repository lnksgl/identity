package spring.user;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("insert into users(username, email, password) values (:username, :email, :password)")
    void create(String username, String email, String password);

    @Query("select * from users where username = :username")
    Mono<User> findByUsername(String username);

    @Query("select * from users where email = :email")
    Mono<User> findByEmail(String email);

    @Query("update users set scanDocument = :average where id = :id")
    void updateUser(String average, Long id);
}
