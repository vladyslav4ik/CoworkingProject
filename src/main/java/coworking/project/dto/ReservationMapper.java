package coworking.project.dto;

import coworking.project.models.Person;
import coworking.project.models.Reservation;
import coworking.project.models.WorkPlace;
import coworking.project.services.ProfileService;
import coworking.project.services.WorkPlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Collections;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final WorkPlaceService workPlaceService;

    public ReservationMapper(ModelMapper modelMapper, ProfileService profileService, WorkPlaceService workPlaceService) {
        this.modelMapper = modelMapper;
        this.profileService = profileService;
        this.workPlaceService = workPlaceService;
    }

    public Reservation convertToReservation(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public ReservationDTO convertToReservationDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public void setOtherValues(Reservation reservation, ReservationDTO reservationDTO, Long workPlaceId) {
        WorkPlace workPlace = workPlaceService.findById(workPlaceId);
        Person person = profileService.getPerson();
        reservation.setIsPayed(false);
        reservation.setIsConfirmed(false);
        reservation.setWorkPlace(workPlace);
        reservation.setRenter(person);
        setTime(reservationDTO, reservation);
        saveData(person, workPlace, reservation);
    }

    private void setTime(ReservationDTO reservationDTO, Reservation reservation) {
        if (reservationDTO.getTime().equals("first")) {
            reservation.setTimeFrom(LocalTime.of(8, 0));
            reservation.setTimeTo(LocalTime.of(13, 0));
            reservation.setPriceTotal(800.0);
        } else if (reservationDTO.getTime().equals("second")) {
            reservation.setTimeFrom(LocalTime.of(13, 0));
            reservation.setTimeTo(LocalTime.of(18, 0));
            reservation.setPriceTotal(800.0);
        } else {
            reservation.setTimeFrom(LocalTime.of(8, 0));
            reservation.setTimeTo(LocalTime.of(18, 0));
            reservation.setPriceTotal(600.0);
        }
    }

    private void saveData(Person person, WorkPlace workPlace, Reservation reservation) {
        if (person.getReservations().isEmpty())
            person.setReservations(Collections.singletonList(reservation));
        else
            person.getReservations().add(reservation);
        System.err.println(person.getReservations());
        if (workPlace.getReservations().isEmpty())
            workPlace.setReservations(Collections.singletonList(reservation));
        else
            workPlace.getReservations().add(reservation);
        System.err.println(workPlace.getReservations());
    }
}