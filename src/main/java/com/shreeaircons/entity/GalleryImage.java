package com.shreeaircons.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String imageUrl; // Path or URL to image
    
    @Column(nullable = false)
    private String serviceType; // AC, Refrigerator, Washing Machine
    
    private String imageType; // before, after, or general
    
    @Column(columnDefinition = "TEXT")
    private String imageDescription;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    private LocalDateTime uploadedAt;
    
    @PrePersist
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }
}
