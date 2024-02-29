// RatingRepo.java
package com.example.sanrio.Repo;

import com.example.sanrio.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
    Rating findRatingByUserId(Long userId);
}
