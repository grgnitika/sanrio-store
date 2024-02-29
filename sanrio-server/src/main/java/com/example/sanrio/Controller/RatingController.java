package com.example.sanrio.Controller;

import com.example.sanrio.Entity.Rating;
import com.example.sanrio.Pojo.RatingPojo;
import com.example.sanrio.Service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitRating(@RequestBody RatingPojo ratingPojo) {
        try {
            ratingService.saveRating(ratingPojo);
            return ResponseEntity.ok("Rating submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting rating.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/getRatingByUserId/{userId}")
    public ResponseEntity<Rating> getRatingByUser(@PathVariable Long userId) {
        Rating rate = ratingService.getRateByUser(userId);
        return ResponseEntity.ok(rate);
    }
}
