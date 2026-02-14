package api.config;

import engine.engine.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConfig {

    @Bean
    public Engine engine() {
        return new EngineImpl();
    }
}
