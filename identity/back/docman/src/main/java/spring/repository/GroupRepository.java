package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.model.Group;
import spring.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByName(String name);

    @Modifying
    @Query("update Group u set u.name = ?1, u.content = ?2 where u.id = ?3")
    void updateGroup(String name, String content, Long id);
}
