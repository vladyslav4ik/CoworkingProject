package coworking.project.services;

import coworking.project.models.Person;
import coworking.project.repositories.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationService {
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final PeopleRepository peopleRepository;


    public AuthorizationService(EmailService emailService, PasswordEncoder passwordEncoder, PeopleRepository peopleRepository) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void save(Person person) {
        person.setRole("ROLE_USER");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
        emailService.sendSignUpEmail(person.getEmail());
    }
}