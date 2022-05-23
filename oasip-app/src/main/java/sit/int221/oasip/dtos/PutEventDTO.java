package sit.int221.oasip.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PutEventDTO {

    @NotNull
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
