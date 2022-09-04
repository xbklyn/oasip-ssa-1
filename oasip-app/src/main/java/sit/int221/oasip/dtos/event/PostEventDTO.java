package sit.int221.oasip.dtos.event;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostEventDTO {

    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String bookingName;

    @NotNull
    @Size(min = 1 , max = 100 , message = "Size must be between 1 and 100")
    private String bookingEmail;

    @NotNull
    private Date eventStartTime;

    @NotNull
    private Integer categoryId;

    @Size(min = 0, max = 500 , message = "Size must be less than 500")
    private String eventNotes;

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getEventNotes() {
        return eventNotes;
    }

    public void setEventNotes(String eventNotes) {
        this.eventNotes = eventNotes;
    }
}
