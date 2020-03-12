package spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@AllArgsConstructor
@EnableR2dbcRepositories
public class ReactivePostgresConfig extends AbstractR2dbcConfiguration {

    private final ObjectMapper objectMapper;

    private final DatasourceProps datasourceProps;

    /** @noinspection NullableProblems*/
    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(datasourceProps.getPrefix() + datasourceProps.getUsername() + ":" +
                datasourceProps.getPassword() + "@" + datasourceProps.getHost() + ":" + datasourceProps.getPort() +
                "/" + datasourceProps.getDatabase());
    }
}
