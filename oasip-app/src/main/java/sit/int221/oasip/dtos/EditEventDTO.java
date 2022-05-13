package sit.int221.oasip.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter @Setter
public class EditEventDTO {
    private String eventNotes;
    private Date eventDate;
    private Time eventStartTime;
}   
