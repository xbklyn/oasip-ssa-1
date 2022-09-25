package sit.int221.oasip.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId", nullable = false)
    private Integer bookingId;

    @Column(name = "bookingName", nullable = false, length = 100)
    private String bookingName;

    @Column(name = "bookingEmail", nullable = false, length = 100)
    private String bookingEmail;

    @Column(name = "eventStartTime", nullable = false)
    private Date eventStartTime;

    @Column(name = "eventEndTime" , nullable = false)
    private Date eventEndTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @Column(name = "eventNotes", length = 500)
    private String eventNotes;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "eventCategoryId", nullable = false)
    private Eventcategory eventCategory;

    @ManyToOne
    @JoinColumn(name = "userId" , nullable = true)
    private User user;


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

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

    public Date getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public Integer getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Integer eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventNotes() {
        return eventNotes;
    }

    public void setEventNotes(String eventNotes) {
        this.eventNotes = eventNotes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Eventcategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(Eventcategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}