package com.shreeaircons.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String customerName;
    
    @Column(nullable = false)
    private String customerEmail;
    
    @Column(nullable = false)
    private String customerPhone;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String reviewText;
    
    @Column(nullable = false)
    private Integer rating; // 1-5 stars
    
    private String serviceType; // AC, Refrigerator, Washing Machine
    
    @Column(nullable = false)
    private Boolean isApproved = false; // Admin approval required
    
    private LocalDateTime createdAt;
    
    private LocalDateTime approvedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
