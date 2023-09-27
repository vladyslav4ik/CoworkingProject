package coworking.project.util.model;

import coworking.project.models.Person;

public class PersonWithEmailAndPassword extends Person {
    private PersonWithEmailAndPassword() {}

    public PersonWithEmailAndPassword(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
}
