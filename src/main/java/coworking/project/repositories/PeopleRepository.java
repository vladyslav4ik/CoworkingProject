package coworking.project.repositories;

import coworking.project.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);

    void deleteById(Long id);
}
