package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sit.int221.oasip.entities.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event> findAllByEventCategory_CategoryId(Integer id);

//    @Modifying(flushAutomatically = true)
//    @Query(value = "UPDATE Event e SET e.status = 2 WHERE e.eventStartTime > :now and e.eventEndTime < :now")
//    public void checkStatus(@Param("now") Date now);
}
