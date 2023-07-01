package coworking.project.controllers;

import coworking.project.dto.ReservationDTO;
import coworking.project.dto.ReservationMapper;
import coworking.project.dto.WorkPlaceMapper;
import coworking.project.models.Reservation;
import coworking.project.services.RatingServiceImpl;
import coworking.project.services.ReservationServiceImpl;
import coworking.project.services.WorkPlaceServiceImpl;
import coworking.project.util.ReservationValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workPlaces")
public class WorkPlaceController {
    private final RatingServiceImpl ratingServiceImpl;
    private final WorkPlaceMapper workPlaceMapper;
    private final WorkPlaceServiceImpl workPlaceServiceImpl;
    private final ReservationMapper reservationMapper;
    private final ReservationServiceImpl reservationServiceImpl;
    private final ReservationValidator reservationValidator;

    public WorkPlaceController(RatingServiceImpl ratingServiceImpl, WorkPlaceMapper workPlaceMapper, WorkPlaceServiceImpl workPlaceServiceImpl, ReservationMapper
            reservationMapper, ReservationServiceImpl reservationServiceImpl, ReservationValidator reservationValidator) {
        this.ratingServiceImpl = ratingServiceImpl;
        this.workPlaceMapper = workPlaceMapper;
        this.workPlaceServiceImpl = workPlaceServiceImpl;
        this.reservationMapper = reservationMapper;
        this.reservationServiceImpl = reservationServiceImpl;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping()
    public String getWorkPlaces(Model model) {
        model.addAttribute("title", "Каталог робочих місць");
        model.addAttribute("workPlaces", workPlaceServiceImpl.findAll()
                .stream()
                .map(workPlaceMapper::convertToWorkPlaceDTO)
                .collect(Collectors.toList()));
        return "work_places/main";
    }

    @GetMapping("/{id}")
    public String getWorkPlace(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Робоче місце № " + id);
        model.addAttribute("workPlace", workPlaceMapper.convertToWorkPlaceDTO(workPlaceServiceImpl.findById(id)));
        return "work_places/show";
    }

    @GetMapping("/{id}/reservation")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String getReservationForm(@PathVariable("id") Long id, Model model,
                                     @ModelAttribute("reservation") ReservationDTO reservationDTO) {
        model.addAttribute("title", "Вибір дати");
        model.addAttribute("workPlace",
                workPlaceMapper.convertToWorkPlaceDTO(workPlaceServiceImpl.findById(id)));
        return "reservations/chooseDate";
    }

    @GetMapping("/{id}/reservation/new")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String createNewReservation(@PathVariable("id") Long id, Model model,
                                       @ModelAttribute("reservation") @Valid ReservationDTO reservationDTO
            , BindingResult bindingResult) {
        model.addAttribute("title", "Створення запиту на бронювання");
        model.addAttribute("workPlace",
                workPlaceMapper.convertToWorkPlaceDTO(workPlaceServiceImpl.findById(id)));
        reservationValidator.validate(reservationDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "/reservations/chooseDate";
        reservationMapper.updateReservationDTO(reservationDTO, id, reservationDTO.getRentDay());
        model.addAttribute("reservation", reservationDTO);
        return "reservations/createReservation";
    }

    @PostMapping("/{id}/reservation/new")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'DEVELOPER')")
    public String saveReservation(@PathVariable("id") Long id, @ModelAttribute("reservation") ReservationDTO
            reservationDTO) {
        Reservation reservation = reservationMapper.convertToReservation(reservationDTO);
        reservationMapper.setOtherValues(reservation, reservationDTO, id);
        reservationServiceImpl.save(reservation);
        return "redirect:/";
    }

    @GetMapping("/status")
    public String getStatistic(Model model) {
        model.addAttribute("title", "Статус робочих місць");
        model.addAttribute("list", workPlaceServiceImpl.findAll());
        return "work_places/work_places_now";
    }

    @GetMapping("/rating")
    public String getRating(Model model) {
        model.addAttribute("title", "Топ-5");
        model.addAttribute("rating", ratingServiceImpl.findTop5ByOrderByNumberOfUsingDesc());
        return "work_places/rating";
    }
}