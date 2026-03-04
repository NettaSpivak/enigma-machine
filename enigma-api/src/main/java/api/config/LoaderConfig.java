package api.config;

import loader.Loader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoaderConfig {

    @Bean
    public Loader loader() {
        return new Loader();
    }
}