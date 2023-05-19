package coworking.project.util;

import coworking.project.dto.ReservationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationDTO reservationDTO = (ReservationDTO) target;
        if (reservationDTO.getRentDay().getDayOfYear() < LocalDate.now().getDayOfYear())
            errors.rejectValue("rentDay", "", "The rent day can't be earlier than today!");
    }
}
