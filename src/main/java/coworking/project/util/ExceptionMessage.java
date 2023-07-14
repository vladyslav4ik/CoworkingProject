package coworking.project.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ExceptionMessage {
    private ExceptionMessage() {
    }

    public static String create(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            builder.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        return builder.toString();
    }
}
