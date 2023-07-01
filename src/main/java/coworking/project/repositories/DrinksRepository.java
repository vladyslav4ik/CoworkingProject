package coworking.project.repositories;

import coworking.project.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByName(String name);
    void deleteByName(String name);
}
