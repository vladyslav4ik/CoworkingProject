package coworking.project.controllers;

import coworking.project.services.WorkPlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workPlaces")
public class WorkPlaceController {
    private final WorkPlaceService workPlaceService;

    public WorkPlaceController(WorkPlaceService workPlaceService) {
        this.workPlaceService = workPlaceService;
    }


    @GetMapping()
    public String getWorkPlaces(Model model) {
        model.addAttribute("workPlaces", workPlaceService.findAvailableWorkPlaces());
        return "work_places/main";
    }

    @GetMapping("/{id}")
    public String getWorkPlace(@PathVariable("id") Long id, Model model) {
        model.addAttribute("workPlace", workPlaceService.findById(id));
        return "work_places/show";
    }
}
