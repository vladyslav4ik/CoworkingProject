package coworking.project.controllers;

import coworking.project.dto.PersonMapper;
import coworking.project.models.Person;
import coworking.project.services.AdminService;
import coworking.project.services.PeopleServiceImpl;
import coworking.project.services.ReservationServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final PeopleServiceImpl peopleServiceImpl;
    private final ReservationServiceImpl reservationServiceImpl;

    public AdminController(AdminService adminService, PersonMapper personMapper, PeopleServiceImpl peopleServiceImpl, ReservationServiceImpl reservationServiceImpl) {
        this.adminService = adminService;
        this.personMapper = personMapper;
        this.peopleServiceImpl = peopleServiceImpl;
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public String getAdminPage(Model model) {
        model.addAttribute("users", adminService.findAll()
                .stream()
                .map(personMapper::convertToPersonDTO)
                .collect(Collectors.toList()));
        return "admin/admin";
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public String getReservations(Model model) {
        model.addAttribute("reservationsToConfirm", reservationServiceImpl.findPayedReservations());
        return "admin/reservations";
    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public String confirmReservation(@PathVariable("id") Long id) {
        adminService.confirmReservation(id);
        return "redirect:/admin/reservations";
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public String setAdminRole(@PathVariable Long id) {
        Person person = peopleServiceImpl.findById(id);
        person.setRole("ROLE_ADMIN");
        peopleServiceImpl.update(id, person);
        return "redirect:/admin";
    }
}