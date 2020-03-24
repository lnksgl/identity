import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

    Long id;
    String username;
    String email;
    String password;
    String scanUser;
    String scanDocument;
}
