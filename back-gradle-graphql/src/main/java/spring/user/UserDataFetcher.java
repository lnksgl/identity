package spring.user;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class UserDataFetcher implements DataFetcher<User> {

    UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment environment) {
        return userRepository.findById(Long.valueOf(environment.getArgument("id"))).orElseThrow(() -> new UserNotFoundException("For id"));
    }
}
