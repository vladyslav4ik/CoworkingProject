package coworking.project.services;

import coworking.project.exceptions.ReservationNotFoundException;
import coworking.project.models.Reservation;
import coworking.project.repositories.ReservationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationsRepository reservationsRepository;

    public ReservationService(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    public List<Reservation> findAll() {
        return reservationsRepository.findAll();
    }

    public Reservation findById(Long id) {
        return reservationsRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    public List<Reservation> findPayedReservations() {
        return reservationsRepository.findPayedReservationsToConfirm();
    }

    @Transactional
    public void payReservation(Long id) {
        Reservation reservation = reservationsRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservation.setIsPayed(true);
        reservationsRepository.save(reservation);
    }

    @Transactional
    public void save(Reservation reservation) {
        reservationsRepository.save(reservation);
    }
}
