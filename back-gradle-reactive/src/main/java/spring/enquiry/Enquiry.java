package spring.enquiry;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@Table("enquiry")
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Enquiry {

    @Id
    @Column("id")
    Long id;
    @Column("idusers")
    Long idUsers;
    @Column("idgroups")
    Long idGroups;
    @Column("createdon")
    Instant createdOn;
}
