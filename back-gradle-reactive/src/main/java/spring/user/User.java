package spring.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("users")
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column("id")
    Long id;
    @Column
    @NonNull
    String username;
    @Column
    @NonNull
    String email;
    @Column
    @NonNull
    String password;
    @Column("scanuser")
    String scanUser;
    @Column("scandocument")
    String scanDocument;
}
