package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRoleName(String name);
}
