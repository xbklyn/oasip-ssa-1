package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findAllByEventCategoryId(Integer id);
}
