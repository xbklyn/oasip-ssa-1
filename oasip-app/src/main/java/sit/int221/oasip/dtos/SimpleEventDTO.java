package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
public class SimpleEventDTO {
    private Integer id;
    private String bookingName;
    private Date eventStartTime;
    private Integer eventDuration;
    private Integer categoryId;
    private String categoryName;
    private String eventNotes;
    private String statusName;
}
