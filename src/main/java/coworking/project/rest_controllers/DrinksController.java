package coworking.project.rest_controllers;

import coworking.project.dto.DrinkDTO;
import coworking.project.dto.DrinkMapper;
import coworking.project.exceptions.DrinkNotFoundException;
import coworking.project.exceptions.InvalidDrinkParametersException;
import coworking.project.services.DrinkServiceImpl;
import coworking.project.util.ErrorResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    public List<DrinkDTO> getDrinks() {
        return drinkService.getDrinks()
                .stream()
                .map(drinkMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get")
    public DrinkDTO getDrink(@Param("name") String name) {
        return drinkMapper.convertToDTO(drinkService.getDrinkByName(name));
    }

    @PostMapping("/new")
    public HttpStatus addDrink(@RequestBody @Valid DrinkDTO drinkDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throwException(bindingResult);
        drinkService.saveDrink(drinkMapper.convertToDrink(drinkDTO));
        return HttpStatus.OK;
    }

    @PatchMapping("/update")
    public HttpStatus updateDrink(@RequestBody @Valid DrinkDTO drinkDTO, @Param("name") String name,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throwException(bindingResult);
        drinkService.updateDrink(name, drinkMapper.convertToDrink(drinkDTO));
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteDrink(@Param("name") String name) {
        drinkService.deleteDrinkByName(name);
        return HttpStatus.OK;
    }

    //?!
    private void throwException(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            builder.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        throw new InvalidDrinkParametersException(builder.toString());
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> catchDrinkNotFound(DrinkNotFoundException e) {
        ErrorResponse response = new ErrorResponse("Drink with this name was not found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> cathchInvalidDrink(InvalidDrinkParametersException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}