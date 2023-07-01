package coworking.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class DrinkDTO {
    @NotEmpty(message = "Name of drink can not be empty!")
    @Size(min = 2, max = 100, message = "The length of name should be in range of 2 and 100!")
    private String name;

    @NotNull(message = "Cocktail should have a price!")
    @Min(value = 0, message = "Price can not be equal 0 or smaller!")
    private Float price;

    @NotEmpty(message = "Drink has to contain ingredients!")
    private String ingredients;
}