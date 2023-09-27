package coworking.project.util.fabric;

import coworking.project.models.Person;
import coworking.project.util.model.PersonType;
import coworking.project.util.model.PersonWithEmail;
import coworking.project.util.model.PersonWithEmailAndPassword;

public class PersonFabric {
    public static Person getPerson(PersonType type) {
        switch (type) {
            case PERSON_WITH_EMAIL:
                return new PersonWithEmail("some@email");
            case PERSON_WITH_EMAIL_AND_PASSWORD:
                return new PersonWithEmailAndPassword("some@email", "password");
        }
        return new Person();
    }
}
