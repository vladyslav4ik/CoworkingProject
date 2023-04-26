package coworking.project.controllers;

import coworking.project.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String getUserProfile(Model model) {
        model.addAttribute("person", profileService.getPerson());
        return "user/profile";
    }

    @GetMapping("/edit")
    public String editUserProfile() {
        return "user/edit";
    }
}