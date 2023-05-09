package coworking.project.controllers;

import coworking.project.dto.PersonMapper;
import coworking.project.models.Person;
import coworking.project.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final PersonMapper personMapper;
    private final ProfileService profileService;

    public ProfileController(PersonMapper personMapper, ProfileService profileService) {
        this.personMapper = personMapper;
        this.profileService = profileService;
    }

    @GetMapping()
    public String getUserProfile(Model model) {
        Person person = profileService.getPerson();
        model.addAttribute("person", personMapper.convertToPersonDTO(person));
        return "user/profile";
    }

    @GetMapping("/edit")
    public String editUserProfile() {
        return "user/edit";
    }
}