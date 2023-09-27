package coworking.project.util.model;

import coworking.project.models.Person;

public class PersonWithEmail extends Person {
    private PersonWithEmail() {}

    public PersonWithEmail(String email) {
        this.setEmail(email);
    }
}