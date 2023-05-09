package coworking.project.controllers;

import coworking.project.repositories.WorkPlacesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping()
    public String getHomePage() {
        return "index";
    }
}