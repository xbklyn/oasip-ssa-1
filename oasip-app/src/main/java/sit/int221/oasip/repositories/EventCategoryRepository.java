package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.Eventcategory;

public interface EventCategoryRepository extends JpaRepository<Eventcategory, Integer> {
}
