package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sit.int221.oasip.entities.Eventcategory;

import java.util.List;

public interface EventCategoryRepository extends JpaRepository<Eventcategory, Integer> {

    @Query(value = "SELECT * FROM eventCategories WHERE lower(:name) = lower(eventCategoryName) AND (:id) != (categoryId)" , nativeQuery = true)
    public List<Eventcategory> checkUniqueName(@Param("name") String name, @Param("id") Integer id);
}
