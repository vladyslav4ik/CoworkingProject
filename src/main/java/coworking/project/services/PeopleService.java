package coworking.project.services;

import coworking.project.models.Person;

import java.util.Optional;

public interface PeopleService {
    Person findById(Long id);
    Optional<Person> findByUsername(String username);
    Optional<Person> findByEmail(String email);
    void update(Long id, Person updatedPerson);
    void delete(Long id);
}
