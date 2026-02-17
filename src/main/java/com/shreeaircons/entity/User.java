package com.shreeaircons.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password; // Should be hashed
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role; // DEVELOPER_ADMIN, CLIENT_ADMIN
    
    @Column(nullable = false)
    private String fullName;
    
    private String phoneNumber;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime lastLogin;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

enum UserRole {
    DEVELOPER_ADMIN, CLIENT_ADMIN
}
