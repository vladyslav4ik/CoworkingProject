package coworking.project.controllers;

import coworking.project.models.Person;
import coworking.project.services.AuthorizationService;
import coworking.project.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {
    private final PersonValidator personValidator;
    private final AuthorizationService authorizationService;

    public AuthorizationController(PersonValidator personValidator, AuthorizationService authorizationService) {
        this.personValidator = personValidator;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignupPage(@ModelAttribute("person") Person person) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String getRegisteredPerson(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/signup";
        authorizationService.save(person);
        return "redirect:/home";
    }
}