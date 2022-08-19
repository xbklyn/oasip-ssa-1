package sit.int221.oasip.dtos;

import org.joda.time.DateTime;
import sit.int221.oasip.entities.Role;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

public class SimpleUserDTO {
    private String userName;
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
