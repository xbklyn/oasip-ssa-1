package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sit.int221.oasip.entities.Role;
import sit.int221.oasip.entities.User;

import java.time.Instant;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByRole(Role role);

    @Query(value = "select * from users where date(createdOn) = :createdOn" , nativeQuery = true)
    public List<User> findByCreatedOn(
            @Param("createdOn") String createdOn);

    @Query(value = "select * from users where date(updatedOn) = :updatedOn" , nativeQuery = true)
    public List<User> findByUpdatedOn(@Param("updatedOn") String date);
}
