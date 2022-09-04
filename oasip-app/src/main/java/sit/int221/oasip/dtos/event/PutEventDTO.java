package sit.int221.oasip.dtos.event;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PutEventDTO {

    @Size(max = 500 , message = "Size must be less than 500")
    private String eventNotes;

    @NotNull
    private Date eventStartTime;


    public String getEventNotes() {
        return eventNotes;
    }

    public void setEventNotes(String eventNotes) {
        this.eventNotes = eventNotes;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }
}
