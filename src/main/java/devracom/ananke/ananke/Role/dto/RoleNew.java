package devracom.ananke.ananke.Role.dto;

public class RoleNew {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleNew{" +
                "name='" + name + '\'' +
                '}';
    }
}
