package coworking.project.util;

import coworking.project.models.Reservation;
import coworking.project.services.ReservationService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReservationValidator implements Validator {
    private final ReservationService reservationService;

    public ReservationValidator(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reservation reserv = (Reservation) target;
        if (reserv.getTimeFrom().getHour() > reserv.getTimeTo().getHour()) {
            errors.rejectValue("timeFrom", "", "");
            errors.rejectValue("timeTo", "", "");
        }
    }
}
