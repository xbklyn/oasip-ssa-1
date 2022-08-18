package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUserName(String username);
}
