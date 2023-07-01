package coworking.project.services;

import coworking.project.models.Person;

import java.util.List;

public interface AdminService {
    List<Person> findAll();
    void confirmReservation(Long id);
}
