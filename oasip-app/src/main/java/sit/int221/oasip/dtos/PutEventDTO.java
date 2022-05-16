package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class PutEventDTO {
    private String eventNotes;
    private Date eventStartTime;
}
