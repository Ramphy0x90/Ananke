package devracom.ananke.ananke.Role;

import devracom.ananke.ananke.Role.dto.RoleNew;
import devracom.ananke.ananke.Role.exceptions.RoleAlreadyExistsException;
import devracom.ananke.ananke.Role.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Get all roles
     * @return List<Role>
     */
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    /**
     * Get role by given id
     * @param id Role id
     * @return Role
     */
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(String.format("Role with ID: %s not found", id))
        );
    }

    /**
     * Get role by given name
     * @param name Role name
     * @return Role
     */
    public Role getRole(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(
                () -> new RoleNotFoundException(String.format("Role with name: %s not found", name))
        );
    }

    /**
     * Create a new role and return the created role
     * @param role role data
     * @return Role
     */
    public Role createRole(RoleNew role) {
        Optional<Role> findRole = roleRepository.findRoleByName(role.getName());

        if(findRole.isPresent()) {
            throw new RoleAlreadyExistsException(String.format("Role: %s already exists", role.getName()));
        } else {
            Role newRole = new Role(role.getName());
            return roleRepository.save(newRole);
        }
    }

    /**
     * Delete role by given id
     * @param id role id
     */
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(String.format("Role with ID: %s not found", id))
        );

        roleRepository.deleteById(id);
    }
}
