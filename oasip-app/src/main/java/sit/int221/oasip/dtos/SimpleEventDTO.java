package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Getter @Setter
public class SimpleEventDTO {
    private Integer eventId;
    private String bookingName;
    private Date eventDate;
    private Time eventStartTime;
    private Integer eventDuration;
    private String eventCategoryName;
    private String eventNotes;
}
