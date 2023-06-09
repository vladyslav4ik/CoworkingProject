package coworking.project.repositories;

import coworking.project.models.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlacesRepository extends JpaRepository<WorkPlace, Long> {
    @Query(value = "SELECT * FROM work_place WHERE is_available = true", nativeQuery = true)
    List<WorkPlace> findAvailableWorkPlaces();
}