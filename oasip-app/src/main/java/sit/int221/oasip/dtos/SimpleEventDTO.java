package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter @Setter
public class SimpleEventDTO {
    private Integer id;
    private String bookingName;
    private Date eventDate;
    private Time eventStartTime;
    private Integer eventDuration;
    private Integer categoryId;
    private String categoryName;
    private String eventNotes;
}
