package coworking.project.services;

import coworking.project.models.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Reservation findById(Long id);
    List<Reservation> findPayedReservations();
    List<Reservation> findAllConfirmedReservations();
    List<Reservation> findAllByWorkPlaceAndRentDay(Long workPlaceId, LocalDate date);
    void payReservation(Long id);
    void save(Reservation reservation);
}
