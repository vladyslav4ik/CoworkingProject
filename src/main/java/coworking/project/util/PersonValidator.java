package coworking.project.util;

import coworking.project.models.Person;
import coworking.project.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleService.findByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "", "");
        if (peopleService.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "", "");
    }
}