package coworking.project.services;

import coworking.project.models.Person;
import coworking.project.repositories.PeopleRepository;
import coworking.project.util.fabric.PersonFabric;
import coworking.project.util.model.PersonType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorizationServiceTest {
    @MockBean
    private EmailService emailService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PeopleRepository peopleRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    void save() {
        Person person = PersonFabric.getPerson(PersonType.PERSON_WITH_EMAIL_AND_PASSWORD);

        when(passwordEncoder.encode(person.getPassword())).thenReturn("encoded");
        authorizationService.save(person);

        assertEquals("ROLE_USER", person.getRole());
        assertEquals("encoded", person.getPassword());
        verify(passwordEncoder, times(1)).encode("password");
        verify(peopleRepository, times(1)).save(person);
        verify(emailService, times(1)).sendSignUpEmail(person.getEmail());
    }
}