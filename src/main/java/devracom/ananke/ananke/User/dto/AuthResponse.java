package devracom.ananke.ananke.User.dto;

public class AuthResponse {
    private UserResponse user;
    private JwtResponse jwtResponse;

    public AuthResponse(UserResponse user, JwtResponse jwtResponse) {
        this.user = user;
        this.jwtResponse = jwtResponse;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
