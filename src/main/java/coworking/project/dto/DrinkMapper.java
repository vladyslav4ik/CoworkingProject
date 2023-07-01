package coworking.project.dto;

import coworking.project.models.Drink;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DrinkMapper {
    private final ModelMapper modelMapper;

    public DrinkMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DrinkDTO convertToDTO(Drink drink) {
        return modelMapper.map(drink, DrinkDTO.class);
    }

    public Drink convertToDrink(DrinkDTO drinkDTO) {
        return modelMapper.map(drinkDTO, Drink.class);
    }
}