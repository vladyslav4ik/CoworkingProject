package coworking.project.controllers;

import coworking.project.dto.PersonDTO;
import coworking.project.dto.PersonMapper;
import coworking.project.dto.PersonUpdateDTO;
import coworking.project.dto.PersonUpdateMapper;
import coworking.project.models.Person;
import coworking.project.services.PeopleService;
import coworking.project.services.ProfileService;
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
    private final PeopleService peopleService;
    private final ProfileService profileService;
    private final PersonUpdateMapper personUpdateMapper;

    public ProfileController(PersonMapper personMapper, PeopleService peopleService, ProfileService profileService, PersonUpdateMapper personUpdateMapper) {
        this.personMapper = personMapper;
        this.peopleService = peopleService;
        this.profileService = profileService;
        this.personUpdateMapper = personUpdateMapper;
    }

    @GetMapping()
    public String getUserProfile(Model model) {
        Person person = profileService.getPerson();
        model.addAttribute("person", personMapper.convertToPersonDTO(person));
        return "user/profile";
    }

    @GetMapping("/edit")
    public String editUserProfile(Model model) {
        Person person = profileService.getPerson();
        model.addAttribute("person", personUpdateMapper.convertToPersonUpdateDTO(person));
        return "user/edit";
    }

    @PatchMapping("/edit")
    public String saveChanges(@ModelAttribute("person") @Valid PersonUpdateDTO personUpdateDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/edit";
        Person person = personUpdateMapper.convertToPerson(personUpdateDTO);
        peopleService.update(person.getId(), person);
        return "redirect:/profile";
    }
}