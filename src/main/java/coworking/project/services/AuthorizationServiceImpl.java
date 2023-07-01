package coworking.project.services;

import coworking.project.models.Person;
import coworking.project.repositories.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final EmailServiceImpl emailServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final PeopleRepository peopleRepository;


    public AuthorizationServiceImpl(EmailServiceImpl emailServiceImpl, PasswordEncoder passwordEncoder, PeopleRepository peopleRepository) {
        this.emailServiceImpl = emailServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void save(Person person) {
        person.setRole("ROLE_USER");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
        emailServiceImpl.sendSignUpEmail(person.getEmail());
    }
}