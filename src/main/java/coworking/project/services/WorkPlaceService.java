package coworking.project.services;

import coworking.project.models.WorkPlace;

import java.util.List;

public interface WorkPlaceService {
    List<WorkPlace> findAll();
    WorkPlace findById(Long id);
    void update(WorkPlace workPlace);
}
