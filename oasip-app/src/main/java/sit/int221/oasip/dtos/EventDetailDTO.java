package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter @Setter
public class EventDetailDTO {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Date eventEndTime;
    private Integer eventDuration;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private String eventCategoryDuration;
    private String eventNotes;
}
