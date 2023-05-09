package coworking.project.dto;

import coworking.project.models.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Reservation convertToReservation(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }

    public ReservationDTO convertToReservationDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }
}
