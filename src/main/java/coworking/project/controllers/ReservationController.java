package coworking.project.controllers;

import coworking.project.services.EmailServiceImpl;
import coworking.project.services.ReservationServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final EmailServiceImpl emailServiceImpl;
    private final ReservationServiceImpl reservationServiceImpl;

    public ReservationController(EmailServiceImpl emailServiceImpl, ReservationServiceImpl reservationServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
        this.reservationServiceImpl = reservationServiceImpl;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String payReservation(@PathVariable("id") Long id) {
        reservationServiceImpl.payReservation(id);
        emailServiceImpl.sendSuccessfulPaymentMessage(reservationServiceImpl.findById(id).getRenter().getEmail());
        return "redirect:/profile";
    }
}