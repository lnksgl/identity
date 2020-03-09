package spring.group;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table("groups")
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @Column("id")
    Long id;
    @NonNull
    @Column
    String name;
    @Column
    @NonNull
    String content;
    @Column
    String average;
}
