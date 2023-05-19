package coworking.project.services;


import coworking.project.models.Reservation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {
    private final WorkPlaceService workPlaceService;
    private final ReservationService reservationService;

    public ScheduleService(WorkPlaceService workPlaceService, ReservationService reservationService) {
        this.workPlaceService = workPlaceService;
        this.reservationService = reservationService;
    }

    @Scheduled(fixedDelay = 60_000)
    public void checkActualityOfInformation() {
        List<Reservation> reservations = reservationService.findAllConfirmedReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getTimeFrom().isBefore(LocalTime.now())) {
                reservation.getWorkPlace().setIsAvailable(false);
            }
            if (LocalTime.now().isAfter(reservation.getTimeTo())) {
                reservation.setIsActual(false);
                reservation.getWorkPlace().setIsAvailable(true);
            }
            reservationService.save(reservation);
            workPlaceService.update(reservation.getWorkPlace());
        }
    }
}