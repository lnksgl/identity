package spring.user;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return userRepository.findAll();
    }
}
