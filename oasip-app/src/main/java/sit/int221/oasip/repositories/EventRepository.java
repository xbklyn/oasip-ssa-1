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

    //Check if event is in booked time
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE Event e SET e.status = 2 WHERE current_timestamp BETWEEN e.eventStartTime and e.eventEndTime")
    public void checkStatusOngoing();

    //Check if event is already passed a booked time
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE Event e SET e.status = 1 WHERE e.eventEndTime < current_timestamp")
    public void checkStatusComplete();
}
