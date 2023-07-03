package coworking.project.services;

import coworking.project.exceptions.DrinkNotFoundException;
import coworking.project.models.Drink;
import coworking.project.repositories.DrinksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinksRepository drinksRepository;

    public DrinkServiceImpl(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @Override
    public List<Drink> getDrinks() {
        return drinksRepository.findAll();
    }

    @Override
    public Optional<Drink> getDrinkByName(String name) {
        return drinksRepository.findByName(name);
    }

    @Override
    @Transactional
    public void saveDrink(Drink drinkToSave) {
        drinksRepository.save(drinkToSave);
    }

    @Override
    @Transactional
    public void updateDrink(String name, Drink updatedDrink) {
        Optional<Drink> optional = getDrinkByName(name);
        if (optional.isPresent()) {
            Drink drink = optional.get();
            updatedDrink.setId(drink.getId());
            drinksRepository.save(updatedDrink);
        } else {
            throw new DrinkNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteDrinkByName(String name) {
        Optional<Drink> optional = getDrinkByName(name);
        if (optional.isPresent()) {
            Drink drinkToDelete = optional.get();
            drinksRepository.delete(drinkToDelete);
        } else {
            throw new DrinkNotFoundException();
        }
    }
}