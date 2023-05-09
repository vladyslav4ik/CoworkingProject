package coworking.project.controllers;

import coworking.project.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/new")
    public String getReservationForm() {
        return "reservations/index";
    }

    @PatchMapping("/{id}")
    public String payReservation(@PathVariable("id") Long id) {
        reservationService.payReservation(id);
        return "redirect:/profile";
    }
}
