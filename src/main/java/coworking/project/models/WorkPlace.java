package coworking.project.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WORK_PLACE")
public class WorkPlace {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "workPlace", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "WorkPlace{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
