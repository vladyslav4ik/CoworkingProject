package coworking.project.repositories;

import coworking.project.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM  reservation WHERE is_payed = true AND is_confirmed = false", nativeQuery = true)
    List<Reservation> findPayedReservationsToConfirm();
}
