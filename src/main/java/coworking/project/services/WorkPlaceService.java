package coworking.project.services;

import coworking.project.exceptions.WorkPlaceNotFoundException;
import coworking.project.models.WorkPlace;
import coworking.project.repositories.WorkPlacesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkPlaceService {
    private final WorkPlacesRepository workPlacesRepository;

    public WorkPlaceService(WorkPlacesRepository workPlacesRepository) {
        this.workPlacesRepository = workPlacesRepository;
    }

    public List<WorkPlace> findAll() {
        return workPlacesRepository.findAll();
    }

    public List<WorkPlace> findAvailableWorkPlaces() {
        return workPlacesRepository.findAvailableWorkPlaces();
    }

    public WorkPlace findById(Long id) {
        return workPlacesRepository.findById(id).orElseThrow(WorkPlaceNotFoundException::new);
    }

    @Transactional
    public void update(WorkPlace workPlace) {
        workPlacesRepository.save(workPlace);
    }
}