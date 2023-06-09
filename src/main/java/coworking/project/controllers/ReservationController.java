package coworking.project.controllers;

import coworking.project.services.EmailService;
import coworking.project.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final EmailService emailService;
    private final ReservationService reservationService;

    public ReservationController(EmailService emailService, ReservationService reservationService) {
        this.emailService = emailService;
        this.reservationService = reservationService;
    }

    @PatchMapping("/{id}")
    public String payReservation(@PathVariable("id") Long id) {
        reservationService.payReservation(id);
        emailService.sendSuccessfulPaymentMessage(reservationService.findById(id).getRenter().getEmail());
        return "redirect:/profile";
    }
}