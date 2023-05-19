package coworking.project.repositories;

import coworking.project.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM  reservation WHERE is_payed = true AND is_confirmed = false", nativeQuery = true)
    List<Reservation> findPayedReservationsToConfirm();

    @Query(value = "SELECT * FROM reservation WHERE is_payed=true AND is_confirmed=true " +
            "AND rent_day=CURRENT_DATE() AND is_actual=true", nativeQuery = true)
    List<Reservation> findAllConfirmedReservations();

    @Query(value = "SELECT * FROM reservation r WHERE r.work_place_id = :work_place_id " +
            "AND r.rent_day = :rent_day", nativeQuery = true)
    List<Reservation> findAllByRentDay(@Param("work_place_id") Long workPlaceId,
                                       @Param("rent_day") LocalDate date);
}