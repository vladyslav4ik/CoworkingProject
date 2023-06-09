package coworking.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationDTO {
    private String time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDay;
}