package spring.group;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import spring.group.Group;
import spring.group.GroupRepository;
import spring.user.User;
import spring.user.UserNotFoundException;
import spring.user.UserRepository;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class GroupDataFetcher implements DataFetcher<Group> {

    GroupRepository groupRepository;

    @Override
    public Group get(DataFetchingEnvironment environment) {
        return groupRepository.findById(Long.valueOf(environment.getArgument("id"))).orElseThrow(() -> new GroupNotFoundException("For id"));
    }
}
