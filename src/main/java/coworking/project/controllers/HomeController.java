package coworking.project.controllers;

import coworking.project.services.ProfileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProfileServiceImpl profileServiceImpl;

    public HomeController(ProfileServiceImpl profileServiceImpl) {
        this.profileServiceImpl = profileServiceImpl;
    }


    @GetMapping()
    public String getHomePage(Model model) {
        model.addAttribute("title", "Coworking Space");
        model.addAttribute("user", profileServiceImpl.getPerson());
        return "index";
    }
}