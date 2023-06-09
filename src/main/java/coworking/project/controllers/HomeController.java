package coworking.project.controllers;

import coworking.project.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProfileService profileService;

    public HomeController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping()
    public String getHomePage(Model model) {
        model.addAttribute("title", "Coworking Space");
        model.addAttribute("user", profileService.getPerson());
        return "index";
    }
}