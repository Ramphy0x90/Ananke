package devracom.ananke.ananke.config;

import devracom.ananke.ananke.Role.Role;
import devracom.ananke.ananke.Role.RoleRepository;
import devracom.ananke.ananke.Ticket.models.Priority;
import devracom.ananke.ananke.Ticket.repositories.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class DataConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository,
                                        PriorityRepository priorityRepository) throws IOException {
        return args -> {
            // Default roles reference string
            List<String> defaultRoles = List.of(
                    "ROLE_ADMIN", "ROLE_USER", "ROLE_SUPERVISOR", "ROLE_DIVISION_HEAD", "ROLE_EXTERNAL"
            );

            // Default tickets priorities reference string
            List<String> defaultPriorities = List.of(
                    "High", "Medium", "Low"
            );

            // Create default roles
            for(String role: defaultRoles) {
                roleRepository.save(new Role(role));
            }

            // Create default priorities
            for(int i = 0; i < defaultPriorities.size(); i++) {
                priorityRepository.save(
                        new Priority(defaultPriorities.get(i), defaultPriorities.size() - i)
                );
            }
        };
    }
}
