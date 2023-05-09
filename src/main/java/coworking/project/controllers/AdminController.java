package coworking.project.controllers;

import coworking.project.dto.PersonMapper;
import coworking.project.models.Person;
import coworking.project.services.AdminService;
import coworking.project.services.PeopleService;
import coworking.project.services.ReservationService;
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
    private final ReservationService reservationService;

    public AdminController(AdminService adminService, PersonMapper personMapper, PeopleService peopleService, ReservationService reservationService) {
        this.adminService = adminService;
        this.personMapper = personMapper;
        this.peopleService = peopleService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", adminService.findAll()
                .stream()
                .map(personMapper::convertToPersonDTO).collect(Collectors.toList()));
        return "admin/admin";
    }

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        model.addAttribute("reservationsToConfirm", reservationService.findPayedReservations());
        return "admin/reservations";
    }

    @GetMapping("/reservations/{id}")
    public String confirmReservation(@PathVariable("id") Long id) {
        adminService.confirmReservation(id);
        return "redirect:/admin/reservations";
    }

    @PatchMapping("/{id}")
    public String setAdminRole(@PathVariable Long id) {
        Person person = peopleService.findById(id);
        person.setRole("ROLE_ADMIN");
        peopleService.update(id, person);
        return "redirect:/admin";
    }
}