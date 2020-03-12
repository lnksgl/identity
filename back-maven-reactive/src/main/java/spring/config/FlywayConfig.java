package spring.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class FlywayConfig {

    DatasourceProps datasourceProps;

    @Bean
    @FlywayDataSource
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://" + datasourceProps.getHost() + ":" + datasourceProps.getPort() + "/"
                + datasourceProps.getDatabase());
        driver.setUsername(datasourceProps.getUsername());
        driver.setPassword(datasourceProps.getPassword());
        return driver;
    }

}
