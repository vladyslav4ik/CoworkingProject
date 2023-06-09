package coworking.project.dto;

import coworking.project.models.Person;
import coworking.project.models.Rating;
import coworking.project.models.Reservation;
import coworking.project.models.WorkPlace;
import coworking.project.services.ProfileService;
import coworking.project.services.RatingService;
import coworking.project.services.ReservationService;
import coworking.project.services.WorkPlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final WorkPlaceService workPlaceService;
    private final ReservationService reservationService;

    public ReservationMapper(ModelMapper modelMapper, ProfileService profileService, WorkPlaceService workPlaceService, ReservationService reservationService) {
        this.modelMapper = modelMapper;
        this.profileService = profileService;
        this.workPlaceService = workPlaceService;
        this.reservationService = reservationService;
    }

    public Reservation convertToReservation(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public ReservationDTO convertToReservationDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public void updateReservationDTO(ReservationDTO reservationDTO, Long workPlaceId, LocalDate date) {
        List<Reservation> reservations = reservationService.findAllByWorkPlaceAndRentDay(workPlaceId, date);
        if (!reservations.isEmpty()) {
            if (reservations.size() == 2) {
                reservationDTO.setTime("full");
            }
            if (reservationDTO.getTime() == null) {
                for (Reservation reservation : reservations) {
                    if (reservation.getTimeFrom().getHour() == 8 && reservation.getTimeTo().getHour() == 13)
                        reservationDTO.setTime("first");
                    if (reservation.getTimeFrom().getHour() == 13 && reservation.getTimeTo().getHour() == 18)
                        reservationDTO.setTime("second");
                    if (reservation.getTimeFrom().getHour() == 8 && reservation.getTimeTo().getHour() == 18)
                        reservationDTO.setTime("full");
                }
            }
        }
    }

    public void setOtherValues(Reservation reservation, ReservationDTO reservationDTO, Long workPlaceId) {
        Person person = profileService.getPerson();
        WorkPlace workPlace = workPlaceService.findById(workPlaceId);
        reservation.setIsPayed(false);
        reservation.setIsConfirmed(false);
        reservation.setIsActual(true);
        reservation.setWorkPlace(workPlace);
        reservation.setRenter(person);
        setTime(reservationDTO, reservation);
        saveData(person, workPlace, reservation);
    }

    private void setTime(ReservationDTO reservationDTO, Reservation reservation) {
        if (reservationDTO.getTime().equals("first")) {
            reservation.setTimeFrom(LocalTime.of(8, 0));
            reservation.setTimeTo(LocalTime.of(13, 0));
            reservation.setPriceTotal(500.0);
        } else if (reservationDTO.getTime().equals("second")) {
            reservation.setTimeFrom(LocalTime.of(13, 0));
            reservation.setTimeTo(LocalTime.of(18, 0));
            reservation.setPriceTotal(500.0);
        } else {
            reservation.setTimeFrom(LocalTime.of(8, 0));
            reservation.setTimeTo(LocalTime.of(18, 0));
            reservation.setPriceTotal(400.0);
        }
    }

    private void saveData(Person person, WorkPlace workPlace, Reservation reservation) {
        if (person.getReservations().isEmpty())
            person.setReservations(Collections.singletonList(reservation));
        else
            person.getReservations().add(reservation);
        if (workPlace.getReservations().isEmpty())
            workPlace.setReservations(Collections.singletonList(reservation));
        else
            workPlace.getReservations().add(reservation);
    }
}