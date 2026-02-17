package com.shreeaircons.service;

import com.shreeaircons.entity.Review;
import com.shreeaircons.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    public Review createReview(Review review) {
        review.setIsApproved(false); // Reviews need admin approval
        return reviewRepository.save(review);
    }
    
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    public List<Review> getAllApprovedReviews() {
        return reviewRepository.findByIsApprovedTrue();
    }
    
    public List<Review> getAllPendingReviews() {
        return reviewRepository.findByIsApprovedFalse();
    }
    
    public List<Review> getReviewsByServiceType(String serviceType) {
        return reviewRepository.findByServiceType(serviceType);
    }
    
    public Review approveReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setIsApproved(true);
            review.setApprovedAt(LocalDateTime.now());
            return reviewRepository.save(review);
        }
        throw new RuntimeException("Review not found");
    }
    
    public Review rejectReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setIsApproved(false);
            return reviewRepository.save(review);
        }
        throw new RuntimeException("Review not found");
    }
    
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    
    public Double getAverageRating() {
        Double avgRating = reviewRepository.getAverageRating();
        return avgRating != null ? avgRating : 0.0;
    }
}
