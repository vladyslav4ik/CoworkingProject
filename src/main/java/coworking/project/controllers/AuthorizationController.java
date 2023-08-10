package coworking.project.controllers;

import coworking.project.dto.PersonDTO;
import coworking.project.dto.PersonMapper;
import coworking.project.models.Person;
import coworking.project.services.AuthorizationService;
import coworking.project.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {
    private final PersonMapper personMapper;
    private final PersonValidator personValidator;
    private final AuthorizationService authorizationService;

    public AuthorizationController(PersonMapper personMapper, PersonValidator personValidator, AuthorizationService authorizationService) {
        this.personMapper = personMapper;
        this.personValidator = personValidator;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("title", "Логін");
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignupPage(@ModelAttribute("person") PersonDTO person, Model model) {
        model.addAttribute("title", "Створення акаунту");
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String getRegisteredPerson(@ModelAttribute("person") @Valid PersonDTO personDTO,
                                      BindingResult bindingResult) {
        Person person = personMapper.convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/signup";
        authorizationService.save(person);
        return "redirect:/home";
    }
}