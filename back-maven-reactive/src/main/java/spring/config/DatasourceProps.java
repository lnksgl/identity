package spring.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties("postgres.datasource")
@Configuration
public class DatasourceProps {

    String prefix;
    String host;
    String port;
    String database;
    String username;
    String password;
}
