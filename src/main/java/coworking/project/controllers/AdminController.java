package coworking.project.controllers;

import coworking.project.dto.PersonMapper;
import coworking.project.models.Person;
import coworking.project.services.AdminService;
import coworking.project.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final PersonMapper personMapper;
    private final PeopleService peopleService;

    public AdminController(AdminService adminService, PersonMapper personMapper, PeopleService peopleService) {
        this.adminService = adminService;
        this.personMapper = personMapper;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", adminService.findAll()
                .stream()
                .map(personMapper::convertToPersonDTO).collect(Collectors.toList()));
        return "admin/admin";
    }

    @PatchMapping("/{id}")
    public String setAdminRole(@PathVariable Long id) {
        Person person = peopleService.findById(id);
        person.setRole("ROLE_ADMIN");
        peopleService.update(id, person);
        return "redirect:/admin";
    }
}