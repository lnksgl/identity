package spring.group;

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
@RequestMapping("/api/v1/group/graphql")
public class GroupGraphQLController {

    GroupGraphQLServices groupGraphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllGroups(@RequestBody String query) {
        return new ResponseEntity<>(groupGraphQLService.getGraphQL().execute(query), HttpStatus.OK);
    }
}
