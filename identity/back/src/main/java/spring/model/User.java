package spring.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="users")
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    Long id;
    @Column
    @NotEmpty
    String username;
    @Column
    @NotEmpty
    String email;
    @Column
    @NotEmpty
    String password;
    @Column(name="scanuser")
    String scanUser;
    @Column(name="scandocument")
    String scanDocument;
}
