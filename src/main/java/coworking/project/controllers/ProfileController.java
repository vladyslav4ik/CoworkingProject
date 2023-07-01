package coworking.project.controllers;

import coworking.project.dto.PersonMapper;
import coworking.project.dto.PersonUpdateDTO;
import coworking.project.dto.PersonUpdateMapper;
import coworking.project.models.Person;
import coworking.project.services.PeopleServiceImpl;
import coworking.project.services.ProfileServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final PersonMapper personMapper;
    private final PeopleServiceImpl peopleServiceImpl;
    private final ProfileServiceImpl profileServiceImpl;
    private final PersonUpdateMapper personUpdateMapper;

    public ProfileController(PersonMapper personMapper, PeopleServiceImpl peopleServiceImpl, ProfileServiceImpl profileServiceImpl, PersonUpdateMapper personUpdateMapper) {
        this.personMapper = personMapper;
        this.peopleServiceImpl = peopleServiceImpl;
        this.profileServiceImpl = profileServiceImpl;
        this.personUpdateMapper = personUpdateMapper;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String getUserProfile(Model model) {
        Person person = profileServiceImpl.getPerson();
        model.addAttribute("person", personMapper.convertToPersonDTO(person));
        return "user/profile";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String editUserProfile(Model model) {
        Person person = profileServiceImpl.getPerson();
        model.addAttribute("person", personUpdateMapper.convertToPersonUpdateDTO(person));
        return "user/edit";
    }

    @PatchMapping("/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String saveChanges(@ModelAttribute("person") @Valid PersonUpdateDTO personUpdateDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/edit";
        Person person = personUpdateMapper.convertToPerson(personUpdateDTO);
        peopleServiceImpl.update(person.getId(), person);
        return "redirect:/profile";
    }
}