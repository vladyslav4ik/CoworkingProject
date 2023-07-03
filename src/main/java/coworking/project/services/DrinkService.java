package coworking.project.services;

import coworking.project.models.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinkService {
    List<Drink> getDrinks();
    Optional<Drink> getDrinkByName(String name);
    void saveDrink(Drink drinkToSave);
    void updateDrink(String name, Drink updatedDrink);
    void deleteDrinkByName(String name);
}