package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"app", "api", "dal"})
@EnableJpaRepositories("dal.repository")
@EntityScan("dal.entity")
public class EnigmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnigmaApplication.class, args);
    }
}
