package sit.int221.oasip.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostUserDTO {
    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String userName;

    @NotNull
    @Size(min = 1 , max = 50 , message = "Size must be between 1 and 50")
    private String userEmail;

    @NotNull
    @Size(min = 8 , max = 14 , message = "Size must be between 8 and 14 characters.")
    private String password;

    @NotNull
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
