package devracom.ananke.ananke.config;

import devracom.ananke.ananke.Role.Role;
import devracom.ananke.ananke.Role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class DataConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) throws IOException {
        return args -> {
            List<String> defaultRoles = List.of(
                    "ROLE_ADMIN", "ROLE_USER", "ROLE_SUPERVISOR", "ROLE_DIVISION_HEAD", "ROLE_EXTERNAL"
            );

            for(String role: defaultRoles) {
                roleRepository.save(new Role(role));
            }
        };
    }
}
