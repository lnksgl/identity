package spring.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties("postgres.datasource")
public class DatasourceProps {

    String prefix;
    String host;
    String port;
    String database;
    String username;
    String password;
}
