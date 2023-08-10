package coworking.project.services;

import coworking.project.exceptions.DrinkNotFoundException;
import coworking.project.models.Drink;
import coworking.project.repositories.DrinksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Drink getDrinkByName(String name) {
        return drinksRepository.findByName(name).orElseThrow(DrinkNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveDrink(Drink drinkToSave) {
        drinksRepository.save(drinkToSave);
    }

    @Override
    @Transactional
    public void updateDrink(String name, Drink updatedDrink) {
        Drink drink = getDrinkByName(name);
        updatedDrink.setId(drink.getId());
        drinksRepository.save(updatedDrink);
    }

    @Override
    @Transactional
    public void deleteDrinkByName(String name) {
        getDrinkByName(name);
        drinksRepository.deleteByName(name);
    }
}