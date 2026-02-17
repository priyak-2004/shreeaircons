package com.shreeaircons.repository;

import com.shreeaircons.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPhoneNumber(String phoneNumber);
    List<Booking> findByStatus(String status);
    
    @Query("SELECT b FROM Booking b WHERE b.createdAt BETWEEN :startDate AND :endDate ORDER BY b.createdAt DESC")
    List<Booking> findBookingsByDateRange(@Param("startDate") LocalDateTime startDate, 
                                         @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'PENDING'")
    Long countPendingBookings();
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.status = 'COMPLETED'")
    Long countCompletedBookings();
    
    @Query("SELECT COUNT(b) FROM Booking b")
    Long countAllBookings();
}
