package coworking.project.services;

import coworking.project.exceptions.ReservationNotFoundException;
import coworking.project.models.Person;
import coworking.project.models.Reservation;
import coworking.project.repositories.PeopleRepository;
import coworking.project.repositories.ReservationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final EmailService emailService;
    private final PeopleRepository peopleRepository;
    private final ReservationsRepository reservationsRepository;

    public AdminService(EmailService emailService, PeopleRepository peopleRepository, ReservationsRepository reservationsRepository) {
        this.emailService = emailService;
        this.peopleRepository = peopleRepository;
        this.reservationsRepository = reservationsRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void confirmReservation(Long id) {
        Reservation reservation = reservationsRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
        reservation.setIsConfirmed(true);
        reservationsRepository.save(reservation);
        emailService.sendSuccessfulConfirmedMessage(reservation.getRenter().getEmail());
    }
}