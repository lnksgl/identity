package spring.group;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GroupRepository extends ReactiveCrudRepository<Group, Long> {

    @Query("select * from group where name = :name")
    Mono<Group> findByName(String name);

    @Query("update group set name = :name, content = :content where id = :id")
    void updateGroup(String name, String content, Long id);

    @Query("update group set average = :average where id = :id")
    void updateGroup(String average, Long id);
}
