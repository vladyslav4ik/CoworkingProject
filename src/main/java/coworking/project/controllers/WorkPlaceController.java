package coworking.project.controllers;

import coworking.project.dto.ReservationDTO;
import coworking.project.dto.ReservationMapper;
import coworking.project.dto.WorkPlaceMapper;
import coworking.project.models.Reservation;
import coworking.project.services.ReservationService;
import coworking.project.services.WorkPlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/workPlaces")
public class WorkPlaceController {
    private final WorkPlaceMapper workPlaceMapper;
    private final WorkPlaceService workPlaceService;
    private final ReservationMapper reservationMapper;
    private final ReservationService reservationService;

    public WorkPlaceController(WorkPlaceMapper workPlaceMapper, WorkPlaceService workPlaceService, ReservationMapper
            reservationMapper, ReservationService reservationService) {
        this.workPlaceMapper = workPlaceMapper;
        this.workPlaceService = workPlaceService;
        this.reservationMapper = reservationMapper;
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String getWorkPlaces(Model model) {
        model.addAttribute("workPlaces", workPlaceService.findAvailableWorkPlaces()
                .stream()
                .map(workPlaceMapper::convertToWorkPlaceDTO)
                .collect(Collectors.toList()));
        return "work_places/main";
    }

    @GetMapping("/{id}")
    public String getWorkPlace(@PathVariable("id") Long id, Model model) {
        model.addAttribute("workPlace", workPlaceMapper.convertToWorkPlaceDTO(workPlaceService.findById(id)));
        return "work_places/show";
    }

    @GetMapping("/{id}/reservation")
    public String getReservationForm(@PathVariable("id") Long id, Model model,
                                     @ModelAttribute("reservation") ReservationDTO reservationDTO) {
        model.addAttribute("workPlace",
                workPlaceMapper.convertToWorkPlaceDTO(workPlaceService.findById(id)));
        return "reservations/index";
    }

    @PostMapping("/{id}/reservation")
    public String saveReservation(@PathVariable("id") Long id, @ModelAttribute("reservation") ReservationDTO
            reservationDTO) {
        Reservation reservation = reservationMapper.convertToReservation(reservationDTO);
        reservationMapper.setOtherValues(reservation, reservationDTO, id);
        reservationService.save(reservation);
        return "redirect:/";
    }
}