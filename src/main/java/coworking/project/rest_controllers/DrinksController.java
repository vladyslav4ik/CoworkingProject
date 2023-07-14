package coworking.project.rest_controllers;

import coworking.project.dto.DrinkDTO;
import coworking.project.dto.DrinkMapper;
import coworking.project.exceptions.DrinkNotFoundException;
import coworking.project.exceptions.InvalidDrinkParametersException;
import coworking.project.models.Drink;
import coworking.project.services.DrinkServiceImpl;
import coworking.project.util.ExceptionMessage;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drinks")
public class DrinksController {
    private final DrinkMapper drinkMapper;
    private final DrinkServiceImpl drinkService;

    public DrinksController(DrinkMapper drinkMapper, DrinkServiceImpl drinkService) {
        this.drinkMapper = drinkMapper;
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<DrinkDTO>> getDrinks() {
        return new ResponseEntity<>(drinkService.getDrinks()
                .stream()
                .map(drinkMapper::convertToDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<DrinkDTO> getDrink(@Param("name") String name) {
        Optional<Drink> optional = drinkService.getDrinkByName(name);
        if (!optional.isPresent())
            throw new DrinkNotFoundException();
        return new ResponseEntity<>(drinkMapper.convertToDTO(optional.get()), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Drink> addDrink(@RequestBody @Valid DrinkDTO drinkDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new InvalidDrinkParametersException(ExceptionMessage.create(bindingResult));
        Drink drink = drinkMapper.convertToDrink(drinkDTO);
        drinkService.saveDrink(drink);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Drink> updateDrink(@RequestBody @Valid DrinkDTO drinkDTO, @Param("name") String name,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new InvalidDrinkParametersException(ExceptionMessage.create(bindingResult));
        Drink drink = drinkMapper.convertToDrink(drinkDTO);
        drinkService.updateDrink(name, drink);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteDrink(@Param("name") String name) {
        drinkService.deleteDrinkByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}