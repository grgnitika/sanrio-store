package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Rating;
import com.example.sanrio.Pojo.RatingPojo;
import com.example.sanrio.Repo.RatingRepo;
import com.example.sanrio.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepository;

    @Override
    public Rating saveRating(RatingPojo ratingPojo) {
        Long userId = ratingPojo.getUserId();

        // Check if the user already has a rating
        Rating existingRating = ratingRepository.findRatingByUserId(userId);

        if (existingRating != null) {
            // Update the existing rating
            existingRating.setValue(ratingPojo.getValue());
            return ratingRepository.save(existingRating);
        } else {
            // Save a new rating if the user doesn't have one
            Rating newRating = new Rating();
            newRating.setUserId(userId);
            newRating.setValue(ratingPojo.getValue());
            return ratingRepository.save(newRating);
        }
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRateByUser(Long userId) {
        return ratingRepository.findRatingByUserId(userId);
    }
}
