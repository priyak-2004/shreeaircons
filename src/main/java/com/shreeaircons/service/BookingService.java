package com.shreeaircons.service;

import com.shreeaircons.entity.Booking;
import com.shreeaircons.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;
    
    public Booking createBooking(Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        // Send WhatsApp notification to admin
        notificationService.sendBookingNotification(savedBooking);
        return savedBooking;
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public List<Booking> getBookingsByPhoneNumber(String phoneNumber) {
        return bookingRepository.findByPhoneNumber(phoneNumber);
    }
    
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }
    
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setStatus(bookingDetails.getStatus());
            booking.setServiceDate(bookingDetails.getServiceDate());
            booking.setServiceTime(bookingDetails.getServiceTime());
            booking.setNotes(bookingDetails.getNotes());
            booking.setConfirmedAt(LocalDateTime.now());
            
            Booking updatedBooking = bookingRepository.save(booking);
            // Send notification when booking is updated
            notificationService.sendStatusUpdateNotification(updatedBooking);
            return updatedBooking;
        }
        throw new RuntimeException("Booking not found");
    }
    
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    
    public Long getPendingBookingsCount() {
        return bookingRepository.countPendingBookings();
    }
    
    public Long getCompletedBookingsCount() {
        return bookingRepository.countCompletedBookings();
    }
    
    public Long getTotalBookingsCount() {
        return bookingRepository.countAllBookings();
    }
    
    public List<Booking> getBookingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.findBookingsByDateRange(startDate, endDate);
    }
}
