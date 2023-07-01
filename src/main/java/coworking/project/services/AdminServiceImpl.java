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
public class AdminServiceImpl implements AdminService {
    private final EmailServiceImpl emailServiceImpl;
    private final PeopleRepository peopleRepository;
    private final ReservationsRepository reservationsRepository;

    public AdminServiceImpl(EmailServiceImpl emailServiceImpl, PeopleRepository peopleRepository, ReservationsRepository reservationsRepository) {
        this.emailServiceImpl = emailServiceImpl;
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
        emailServiceImpl.sendSuccessfulConfirmedMessage(reservation.getRenter().getEmail());
    }
}