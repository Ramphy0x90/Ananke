package devracom.ananke.ananke.User;

import devracom.ananke.ananke.Role.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;

/**
 *  User model class. Every user is identified
 *  by an email and a password.
 *
 *  @author  Ramphy Aquino Nova
 *  @version 2022.11.10
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = Collections.emptyList();

    public User() {}

    public User(String email, String password, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        Predicate<Role> roleRemove = (Role roleToRemove) -> (roleToRemove.getName().equals(role.getName()));
        this.roles.removeIf(roleRemove);
    }
}
