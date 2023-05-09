package coworking.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_from")
    private Time timeFrom;

    @Column(name = "time_to")
    private Time timeTo;

    @Column(name = "rent_day")
    private Date rentDay;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "total_price")
    private Double priceTotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_place_id", referencedColumnName = "id")
    private WorkPlace workPlace;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private Person renter;
}