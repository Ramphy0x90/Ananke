package devracom.ananke.ananke.Role;

import devracom.ananke.ananke.Role.dto.RoleNew;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Returns all roles
     * @return List<Role>
     */
    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
    })
    @GetMapping(path = "/all")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Returns a role by give id
     * @param id role id
     * @return Role
     */
    @Operation(summary = "Get role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content),
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.OK);
    }

    /**
     * Create a new role and returns the role created
     * @param role role data
     * @return Role
     */
    @Operation(summary = "Create new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new role"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    @PostMapping(path = "/create")
    public ResponseEntity<Role> createRole(@RequestBody RoleNew role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    /**
     * Delete role by given id and return void
     * @param id role id
     */
    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "401", description = "Authorization denied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
    })
    @DeleteMapping(path = "/delete/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }
}
