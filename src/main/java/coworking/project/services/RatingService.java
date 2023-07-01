package coworking.project.services;

import coworking.project.models.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> findTop5ByOrderByNumberOfUsingDesc();
    void update(Rating rating);
}
