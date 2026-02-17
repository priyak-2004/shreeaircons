package com.shreeaircons.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String customerName;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private String repairType; // AC, Refrigerator, Washing Machine
    
    @Column(nullable = false)
    private String address;
    
    @Column(columnDefinition = "TEXT")
    private String problemDescription;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED, COMPLETED
    
    private LocalDateTime createdAt;
    
    private LocalDateTime confirmedAt;
    
    private LocalDateTime serviceDate; // Admin will set this
    
    private String serviceTime; // Service time set by admin
    
    private String notes; // Admin notes
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = BookingStatus.PENDING;
        }
    }
}

enum BookingStatus {
    PENDING, CONFIRMED, COMPLETED, CANCELLED
}
