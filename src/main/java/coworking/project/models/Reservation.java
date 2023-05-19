package coworking.project.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_from")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeFrom;

    @Column(name = "time_to")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeTo;

    @Column(name = "rent_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDay;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "is_actual")
    private Boolean isActual;

    @Column(name = "total_price")
    private Double priceTotal;

    @ManyToOne
    @JoinColumn(name = "work_place_id")
    private WorkPlace workPlace;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private Person renter;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                ", rentDay=" + rentDay +
                ", isPayed=" + isPayed +
                ", isConfirmed=" + isConfirmed +
                ", priceTotal=" + priceTotal +
                '}';
    }
}