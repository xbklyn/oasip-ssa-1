package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
