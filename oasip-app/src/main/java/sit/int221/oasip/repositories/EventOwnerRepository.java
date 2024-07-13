package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sit.int221.oasip.entities.EventOwner;
import sit.int221.oasip.entities.Eventcategory;
import sit.int221.oasip.entities.User;

import java.util.List;


public interface EventOwnerRepository extends JpaRepository<EventOwner , Integer> {
    public List<EventOwner> findByUser(User user);

    public EventOwner findByEventCategoryAndUser(Eventcategory eventcategory , User user);
}
