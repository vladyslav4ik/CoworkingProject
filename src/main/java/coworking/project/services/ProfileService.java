package coworking.project.services;

import coworking.project.models.Person;
import coworking.project.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final PeopleService peopleService;

    public ProfileService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public Person getPerson() {
        return peopleService.findById(getCurrentUser().getId());
    }

    private Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
