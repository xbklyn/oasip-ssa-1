package sit.int221.oasip.entities;

import javax.persistence.*;

@Entity
@Table(name = "eventCategoryOwners")
public class EventOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryOwnerId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eventCategoryId", nullable = false)
    private Eventcategory eventCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Eventcategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(Eventcategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}