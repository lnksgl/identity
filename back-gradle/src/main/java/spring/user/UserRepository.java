package spring.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
    List<User> findByEmail(String email);
    Mono<User> findById(Long id);

    @Modifying
    @Query("update User  set scanDocument = ?1 where id = ?2")
    void updateUser(String average, Long id);
}
