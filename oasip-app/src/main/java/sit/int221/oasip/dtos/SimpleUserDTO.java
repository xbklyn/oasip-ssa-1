package sit.int221.oasip.dtos;

import org.joda.time.DateTime;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import sit.int221.oasip.entities.Role;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class SimpleUserDTO {
    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String userName;

    @NotNull
    @Size(min = 1 , max = 50 , message = "Size must be between 1 and 50")
    private String userEmail;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
