package spring.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/user/graphql")
public class UserGraphQLController {

    UserGraphQLService userGraphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllUsers(@RequestBody String query) {
        return new ResponseEntity<>(userGraphQLService.getGraphQL().execute(query), HttpStatus.OK);
    }
}
