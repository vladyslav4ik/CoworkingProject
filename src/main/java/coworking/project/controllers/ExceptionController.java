package coworking.project.controllers;

import coworking.project.exceptions.DrinkNotFoundException;
import coworking.project.exceptions.InvalidDrinkParametersException;
import coworking.project.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleDrinkNotFound(DrinkNotFoundException e) {
        ErrorResponse response = new ErrorResponse("Drink with this name was not found!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleInvalidDrinkParameters(InvalidDrinkParametersException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}