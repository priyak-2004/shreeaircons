package com.shreeaircons.controller;

import com.shreeaircons.entity.GalleryImage;
import com.shreeaircons.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/gallery")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GalleryController {
    
    private final GalleryService galleryService;
    
    @PostMapping("/upload")
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
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading image: " + e.getMessage());
        }
    }
    
    @GetMapping("/service/{serviceType}")
    public ResponseEntity<?> getImagesByServiceType(@PathVariable String serviceType) {
        return ResponseEntity.ok(galleryService.getImagesByServiceType(serviceType));
    }
    
    @GetMapping
    public ResponseEntity<?> getAllActiveImages() {
        return ResponseEntity.ok(galleryService.getAllActiveImages());
    }
}
