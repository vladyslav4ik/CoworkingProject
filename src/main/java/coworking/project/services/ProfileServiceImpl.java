package coworking.project.services;

import coworking.project.models.Person;
import coworking.project.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    private final PeopleServiceImpl peopleServiceImpl;

    public ProfileServiceImpl(PeopleServiceImpl peopleServiceImpl) {
        this.peopleServiceImpl = peopleServiceImpl;
    }

    public Person getPerson() {
        Person person = getCurrentUser();
        return (person.getId() == null) ? person : peopleServiceImpl.findById(person.getId());
    }

    private Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser"))
            return new Person();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}