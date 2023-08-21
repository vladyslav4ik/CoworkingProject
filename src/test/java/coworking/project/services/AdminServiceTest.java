package coworking.project.services;

import coworking.project.exceptions.ReservationNotFoundException;
import coworking.project.models.Person;
import coworking.project.models.Reservation;
import coworking.project.repositories.PeopleRepository;
import coworking.project.repositories.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AdminServiceTest {
    @MockBean
    private PeopleRepository peopleRepository;

    @MockBean
    private ReservationsRepository reservationsRepository;

    @MockBean
    private EmailService emailService;

    private final AdminService adminService;

    @Autowired
    AdminServiceTest(AdminService adminService) {
        this.adminService = adminService;
    }

    @Test
    void findAll() {
        when(peopleRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, adminService.findAll().size());

        List<Person> list = new ArrayList<>();
        list.add(new Person());
        list.add(new Person());
        list.add(new Person());

        when(peopleRepository.findAll()).thenReturn(list);
        assertEquals(3, adminService.findAll().size());
        Mockito.verify(peopleRepository, Mockito.times(2)).findAll();
    }

    @Test
    void confirmReservation() {
        Reservation reservation = new Reservation();
        Person person = new Person();
        person.setEmail("some@email");
        reservation.setRenter(person);

        when(reservationsRepository.findById(1L)).thenReturn(Optional.of(reservation));
        adminService.confirmReservation(1L);

        boolean isConfirmed = reservation.getIsConfirmed();
        assertTrue(isConfirmed);
        verify(reservationsRepository, times(1)).save(reservation);
        verify(emailService, times(1))
                .sendSuccessfulConfirmedMessage(person.getEmail());
    }

    @Test
    void confirmReservationFailTest() {
        when(reservationsRepository.findById(1L)).thenThrow(new ReservationNotFoundException());

        assertThrows(ReservationNotFoundException.class, () -> adminService.confirmReservation(1L));

        verify(reservationsRepository, times(0))
                .save(ArgumentMatchers.any(Reservation.class));

        verify(emailService, times(0))
                .sendSuccessfulConfirmedMessage(ArgumentMatchers.anyString());
    }
}