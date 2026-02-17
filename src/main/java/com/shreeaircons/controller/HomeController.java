package com.shreeaircons.controller;

import com.shreeaircons.service.BookingService;
import com.shreeaircons.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final BookingService bookingService;
    private final ReviewService reviewService;
    
    @GetMapping("/")
    public String home(Model model) {
        // Add dashboard statistics
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalBookings", bookingService.getTotalBookingsCount());
        stats.put("pendingBookings", bookingService.getPendingBookingsCount());
        stats.put("completedBookings", bookingService.getCompletedBookingsCount());
        
        model.addAttribute("stats", stats);
        model.addAttribute("averageRating", reviewService.getAverageRating());
        return "index";
    }
    
    @GetMapping("/services")
    public String services(Model model) {
        return "services";
    }
    
    @GetMapping("/booking")
    public String booking(Model model) {
        return "booking";
    }
    
    @GetMapping("/gallery")
    public String gallery(Model model) {
        return "gallery";
    }
    
    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllApprovedReviews());
        return "reviews";
    }
    
    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalBookings", bookingService.getTotalBookingsCount());
        stats.put("pendingBookings", bookingService.getPendingBookingsCount());
        stats.put("completedBookings", bookingService.getCompletedBookingsCount());
        
        model.addAttribute("stats", stats);
        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("pendingReviews", reviewService.getAllPendingReviews());
        return "admin/dashboard";
    }
}
