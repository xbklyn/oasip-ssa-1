package sit.int221.oasip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "statusId")
    private Integer statusId;

    @Column(name = "statusName")
    private String statusName;

    @JsonIgnore
    @OneToMany(mappedBy="status")
    private Set<Event> events;


}
