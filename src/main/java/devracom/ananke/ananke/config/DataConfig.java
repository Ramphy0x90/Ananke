package devracom.ananke.ananke.config;

import devracom.ananke.ananke.Role.Role;
import devracom.ananke.ananke.Role.RoleRepository;
import devracom.ananke.ananke.Role.exceptions.RoleNotFoundException;
import devracom.ananke.ananke.Ticket.models.Priority;
import devracom.ananke.ananke.Ticket.repositories.PriorityRepository;
import devracom.ananke.ananke.User.User;
import devracom.ananke.ananke.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PriorityRepository priorityRepository;
    private final PasswordEncoder passwordEncoder;

    public DataConfig(UserRepository userRepository,
                      RoleRepository roleRepository,
                      PriorityRepository priorityRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.priorityRepository = priorityRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    CommandLineRunner commandLineRunner() throws IOException {
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

            // Init default users
            initDefaultUsers();
        };
    }

    public void initDefaultUsers() {
        Role userRole = roleRepository.findRoleByName("ROLE_ADMIN").orElseThrow(
                () -> new RoleNotFoundException(String.format("Role with name: %s not found", "ROLE_ADMIN"))
        );

        User user = new User(
                "User",
                "Test",
                "user.test@email.com",
                passwordEncoder.encode("password"),
                1,
                List.of(userRole)
        );

        userRepository.save(user);
    }

    public void initTestTickets() {

    }
}
