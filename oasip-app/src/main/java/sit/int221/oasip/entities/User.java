package sit.int221.oasip.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Integer id;

    @Column(name = "userName", nullable = false, length = 100)
    private String userName;

    @Column(name = "userEmail", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "password" , nullable = false, length = 90)
    private String password;

    @Column(name = "createdOn", nullable = false)
    private Instant createdOn;

    @Column(name = "updatedOn", nullable = false)
    private Instant updatedOn;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}