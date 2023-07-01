package coworking.project.services;

import coworking.project.exceptions.PersonNotFoundException;
import coworking.project.models.Person;
import coworking.project.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService{
    private final PeopleRepository peopleRepository;


    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findById(Long id) {
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    @Transactional
    public void update(Long id, Person updatedPerson) {
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}