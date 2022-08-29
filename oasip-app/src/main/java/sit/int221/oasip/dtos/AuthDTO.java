package sit.int221.oasip.dtos;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthDTO {
    @NotNull
    @Size(min = 8 , max = 14 , message = "Size must be between 8 and 14 characters.")
    private String email;
    @NotNull
    @Size(min = 8 , max = 14 , message = "Size must be between 8 and 14 characters.")
    private String rawPassword;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}
