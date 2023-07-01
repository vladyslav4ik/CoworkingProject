package coworking.project.util;

import coworking.project.models.Person;
import coworking.project.services.PeopleServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleServiceImpl peopleServiceImpl;

    public PersonValidator(PeopleServiceImpl peopleServiceImpl) {
        this.peopleServiceImpl = peopleServiceImpl;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleServiceImpl.findByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "", "");
        if (peopleServiceImpl.findByEmail(person.getEmail()).isPresent())
            errors.rejectValue("email", "", "");
    }
}