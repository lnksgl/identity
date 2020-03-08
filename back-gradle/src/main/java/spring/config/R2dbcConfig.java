package spring.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class R2dbcConfig {

//    @NotNull
//    @Override
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
//                .option(DRIVER, "pool")
//                .option(PROTOCOL, "postgresql")
//                .option(HOST, "localhost")
//                .option(USER, "postgres")
//                .option(PASSWORD, "postgres")
//                .option(DATABASE, "identity")
//                .option(MAX_SIZE, 10)
//                .build());
//    }
}
