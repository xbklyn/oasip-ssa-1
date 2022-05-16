package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
public class PostEventDTO {

    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
    private Integer categoryId;
    private String eventNotes;
}
