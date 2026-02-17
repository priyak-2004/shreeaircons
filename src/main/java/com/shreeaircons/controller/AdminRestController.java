package com.shreeaircons.controller;

import com.shreeaircons.entity.Booking;
import com.shreeaircons.entity.Review;
import com.shreeaircons.entity.GalleryImage;
import com.shreeaircons.service.BookingService;
import com.shreeaircons.service.ReviewService;
import com.shreeaircons.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {
    
    private final BookingService bookingService;
    private final ReviewService reviewService;
    private final GalleryService galleryService;
    
    // Booking Management
    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    
    @GetMapping("/bookings/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/bookings/{id}")
    public ResponseEntity<Map<String, Object>> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        try {
            Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Booking updated and customer notified");
            response.put("booking", updatedBooking);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error updating booking: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Map<String, Object>> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Booking deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error deleting booking");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/bookings/status/{status}")
    public ResponseEntity<?> getBookingsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
    }
    
    // Review Management
    @GetMapping("/reviews/pending")
    public ResponseEntity<?> getPendingReviews() {
        return ResponseEntity.ok(reviewService.getAllPendingReviews());
    }
    
    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews() {
        // Consider pagination for large datasets
        return ResponseEntity.ok(reviewService.getAllApprovedReviews());
    }
    
    @PutMapping("/reviews/{id}/approve")
    public ResponseEntity<Map<String, Object>> approveReview(@PathVariable Long id) {
        try {
            Review approvedReview = reviewService.approveReview(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Review approved");
            response.put("review", approvedReview);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error approving review");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/reviews/{id}/reject")
    public ResponseEntity<Map<String, Object>> rejectReview(@PathVariable Long id) {
        try {
            Review rejectedReview = reviewService.rejectReview(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Review rejected");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error rejecting review");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Map<String, Object>> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Review deleted");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error deleting review");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Gallery Management
    @PostMapping("/gallery/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("serviceType") String serviceType,
            @RequestParam(value = "imageType", required = false) String imageType,
            @RequestParam(value = "description", required = false) String description) {
        try {
            GalleryImage image = GalleryImage.builder()
                .serviceType(serviceType)
                .imageType(imageType)
                .imageDescription(description)
                .isActive(true)
                .build();
            
            GalleryImage savedImage = galleryService.uploadImage(image, file);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Image uploaded successfully");
            response.put("image", savedImage);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error uploading image");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/gallery")
    public ResponseEntity<?> getAllGalleryImages() {
        return ResponseEntity.ok(galleryService.getAllImages());
    }
    
    @PutMapping("/gallery/{id}")
    public ResponseEntity<Map<String, Object>> updateGalleryImage(@PathVariable Long id, @RequestBody GalleryImage imageDetails) {
        try {
            GalleryImage updatedImage = galleryService.updateImage(id, imageDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Image updated successfully");
            response.put("image", updatedImage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error updating image");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/gallery/{id}")
    public ResponseEntity<Map<String, Object>> deleteGalleryImage(@PathVariable Long id) {
        try {
            galleryService.deleteImage(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Image deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error deleting image");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Dashboard Statistics
    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBookings", bookingService.getTotalBookingsCount());
        stats.put("pendingBookings", bookingService.getPendingBookingsCount());
        stats.put("completedBookings", bookingService.getCompletedBookingsCount());
        stats.put("pendingReviews", reviewService.getAllPendingReviews().size());
        stats.put("averageRating", reviewService.getAverageRating());
        return ResponseEntity.ok(stats);
    }
}
