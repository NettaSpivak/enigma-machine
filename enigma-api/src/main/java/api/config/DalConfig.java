package api.config;

import dal.registry.MachineRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DalConfig {

    @Bean
    public MachineRegistry machineRegistry() {
        return new MachineRegistry();
    }
}