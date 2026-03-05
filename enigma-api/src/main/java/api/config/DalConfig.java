package api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DalConfig {

    @Bean
    public MachineRegistry machineRegistry() {
        return new MachineRegistry();
    }
}