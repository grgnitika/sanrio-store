// RatingService.java
package com.example.sanrio.Service;

import com.example.sanrio.Entity.Rating;
import com.example.sanrio.Pojo.RatingPojo;

import java.util.List;

public interface RatingService {
    Rating saveRating(RatingPojo ratingPojo);
    List<Rating> getAllRatings();

    Rating getRateByUser(Long userId);

}
