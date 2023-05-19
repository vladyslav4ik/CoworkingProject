package coworking.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkPlaceDTO {
    private Long id;
    private String itemName;
    private String description;
    private Double pricePerHour;
}
