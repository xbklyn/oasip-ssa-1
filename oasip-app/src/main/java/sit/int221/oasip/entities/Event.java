package sit.int221.oasip.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId", nullable = false)
    private Integer id;

    @Column(name = "bookingName", nullable = false, length = 100)
    private String bookingName;

    @Column(name = "bookingEmail", nullable = false, length = 100)
    private String bookingEmail;

    @Column(name = "eventDate", nullable = false)
    private Date eventDate;

    @Column(name = "eventStartTime", nullable = false)
    private Time eventStartTime;

    @Column(name = "eventEndTime" , nullable = false)
    private Time eventEndTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @Column(name = "eventNotes", length = 250)
    private String eventNotes;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "eventCategoryId", nullable = false)
    private Eventcategory eventCategory;

}