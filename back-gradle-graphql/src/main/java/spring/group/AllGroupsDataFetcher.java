package spring.group;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import spring.user.User;
import spring.user.UserRepository;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class AllGroupsDataFetcher implements DataFetcher<List<Group>> {

    GroupRepository groupRepository;

    @Override
    public List<Group> get(DataFetchingEnvironment environment) {
        return groupRepository.findAll();
    }
}
