package coworking.project.services;

import coworking.project.models.Rating;
import coworking.project.repositories.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findTop5ByOrderByNumberOfUsingDesc() {
        return ratingRepository.findTop5ByOrderByNumberOfUsingDesc();
    }

    @Transactional
    public void update(Rating rating) {
        ratingRepository.save(rating);
    }
}