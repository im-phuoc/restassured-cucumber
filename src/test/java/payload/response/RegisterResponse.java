package payload.response;

import java.util.List;

public class RegisterResponse {
    private String username;
    private String email;
    private List<String> roles;

    public RegisterResponse(String username, String email, List<String> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
