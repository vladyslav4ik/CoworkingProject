package coworking.project.controllers;

import coworking.project.dto.ReservationDTO;
import coworking.project.dto.ReservationMapper;
import coworking.project.dto.WorkPlaceMapper;
import coworking.project.models.Reservation;
import coworking.project.services.RatingService;
import coworking.project.services.ReservationService;
import coworking.project.services.WorkPlaceService;
import coworking.project.util.ReservationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workPlaces")
public class WorkPlaceController {
    private final RatingService ratingService;
    private final WorkPlaceMapper workPlaceMapper;
    private final WorkPlaceService workPlaceService;
    private final ReservationMapper reservationMapper;
    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public WorkPlaceController(RatingService ratingService, WorkPlaceMapper workPlaceMapper, WorkPlaceService workPlaceService, ReservationMapper
            reservationMapper, ReservationService reservationService, ReservationValidator reservationValidator) {
        this.ratingService = ratingService;
        this.workPlaceMapper = workPlaceMapper;
        this.workPlaceService = workPlaceService;
        this.reservationMapper = reservationMapper;
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping()
    public String getWorkPlaces(Model model) {
        model.addAttribute("title", "Каталог робочих місць");
        model.addAttribute("workPlaces", workPlaceService.findAll()
                .stream()
                .map(workPlaceMapper::convertToWorkPlaceDTO)
                .collect(Collectors.toList()));
        return "work_places/main";
    }

    @GetMapping("/{id}")
    public String getWorkPlace(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Робоче місце № " + id);
        model.addAttribute("workPlace", workPlaceMapper.convertToWorkPlaceDTO(workPlaceService.findById(id)));
        return "work_places/show";
    }

    @GetMapping("/{id}/reservation")
    public String getReservationForm(@PathVariable("id") Long id, Model model,
                                     @ModelAttribute("reservation") ReservationDTO reservationDTO) {
        model.addAttribute("title", "Вибір дати");
        model.addAttribute("workPlace",
                workPlaceMapper.convertToWorkPlaceDTO(workPlaceService.findById(id)));
        return "reservations/chooseDate";
    }

    @GetMapping("/{id}/reservation/new")
    public String createNewReservation(@PathVariable("id") Long id, Model model,
                                       @ModelAttribute("reservation") @Valid ReservationDTO reservationDTO
            , BindingResult bindingResult) {
        model.addAttribute("title", "Створення запиту на бронювання");
        model.addAttribute("workPlace",
                workPlaceMapper.convertToWorkPlaceDTO(workPlaceService.findById(id)));
        reservationValidator.validate(reservationDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "/reservations/chooseDate";
        reservationMapper.updateReservationDTO(reservationDTO, id, reservationDTO.getRentDay());
        model.addAttribute("reservation", reservationDTO);
        return "reservations/createReservation";
    }

    @PostMapping("/{id}/reservation/new")
    public String saveReservation(@PathVariable("id") Long id, @ModelAttribute("reservation") ReservationDTO
            reservationDTO) {
        Reservation reservation = reservationMapper.convertToReservation(reservationDTO);
        reservationMapper.setOtherValues(reservation, reservationDTO, id);
        reservationService.save(reservation);
        return "redirect:/";
    }

    @GetMapping("/status")
    public String getStatistic(Model model) {
        model.addAttribute("title", "Статус робочих місць");
        model.addAttribute("list", workPlaceService.findAll());
        return "work_places/work_places_now";
    }

    @GetMapping("/rating")
    public String getRating(Model model) {
        model.addAttribute("title", "Топ-5");
        model.addAttribute("rating", ratingService.findTop5ByOrderByNumberOfUsingDesc());
        return "work_places/rating";
    }
}