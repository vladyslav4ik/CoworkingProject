package coworking.project.dto;

import coworking.project.models.Person;
import coworking.project.services.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PersonUpdateMapper {
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;


    public PersonUpdateMapper(ModelMapper modelMapper, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    public Person convertToPerson(PersonUpdateDTO personUpdateDTO) {
        Person person = modelMapper.map(personUpdateDTO, Person.class);
        person.setId(profileService.getPerson().getId());
        person.setRole(profileService.getPerson().getRole());
        person.setPassword(passwordEncoder.encode(personUpdateDTO.getPassword()));
        return person;
    }

    public PersonUpdateDTO convertToPersonUpdateDTO(Person person) {
        return modelMapper.map(person, PersonUpdateDTO.class);
    }
}