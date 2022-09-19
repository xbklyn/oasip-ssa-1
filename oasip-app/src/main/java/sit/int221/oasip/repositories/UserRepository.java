package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sit.int221.oasip.entities.Role;
import sit.int221.oasip.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByRole(Role role);

    @Query(value = "select * from users where date(createdOn) = :createdOn" , nativeQuery = true)
    public List<User> findByCreatedOn(
            @Param("createdOn") String createdOn);

    @Query(value = "select * from users where date(updatedOn) = :updatedOn" , nativeQuery = true)
    public List<User> findByUpdatedOn(@Param("updatedOn") String date);

    public List<User> findByUserName(String name);
    public List<User> findByUserEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "insert into users values(null,:name,:email,:password, current_timestamp, current_timestamp, :roleId)" , nativeQuery = true)
    public Integer create(
            @Param("name") String name,
            @Param("email") String email,
            @Param("password") String password,
            @Param("roleId") Integer id
    );

    @Transactional
    @Modifying
    @Query(value = "update users set userName = :name " +
            ", userEmail = :email " +
            ", roleId = :roleId " +
            ", updatedOn = current_timestamp " +
            "where userId = :userId", nativeQuery = true)
    public Integer update(
            @Param("userId") Integer id,
            @Param("name") String name,
            @Param("email") String email,
            @Param("roleId") Integer roleId
    );

}
