package coworking.project.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/pricing")
    public String getPricingPage(Model model) {
        model.addAttribute("title", "Тарифи");
        return "info/pricing";
    }

    @GetMapping("/aboutUs")
    public String getAboutUsPage(Model model) {
        model.addAttribute("title", "Про організацію");
        return "info/aboutUs";
    }
}