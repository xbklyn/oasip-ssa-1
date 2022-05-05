package sit.int221.oasip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "eventCategories")
public class Eventcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventCategoryId", nullable = false)
    private Integer categoryId;

    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String eventCategoryName;

    @Column(name = "eventCategoryDescription", length = 250)
    private String eventCategoryDescription;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventCategoryDuration;

    @JsonIgnore
    @OneToMany(mappedBy="eventCategory")
    private Set<Event> events;
}