package sit.int221.oasip.dtos;


import java.util.Date;

public class TimeDTO {
    private Date eventStartTime;
    private Date eventEndTime;

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }
}
