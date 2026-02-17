package com.shreeaircons.controller;

import com.shreeaircons.entity.Review;
import com.shreeaircons.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createReview(@RequestBody Review review) {
        try {
            Review savedReview = reviewService.createReview(review);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Review submitted successfully. Awaiting admin approval.");
            response.put("reviewId", savedReview.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error submitting review: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/approved")
    public ResponseEntity<?> getApprovedReviews() {
        return ResponseEntity.ok(reviewService.getAllApprovedReviews());
    }
    
    @GetMapping("/rating")
    public ResponseEntity<?> getAverageRating() {
        Map<String, Object> response = new HashMap<>();
        response.put("averageRating", reviewService.getAverageRating());
        return ResponseEntity.ok(response);
    }
}
