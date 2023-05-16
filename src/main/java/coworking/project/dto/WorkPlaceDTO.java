package coworking.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class WorkPlaceDTO {
    private Long id;
    private String itemName;
    private String description;
    private Double pricePerHour;
}
