package api.config;

import dal.registry.MachineRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import session.SessionRegistry;

@Configuration
public class SessionConfig {

    @Bean
    public SessionRegistry sessionRegistry(MachineRegistry machineRegistry) {
        return new SessionRegistry(machineRegistry);
    }
}