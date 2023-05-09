package coworking.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
public class ReservationDTO {
    private Time timeFrom;
    private Time timeTo;
    private Date rentDay;
}
