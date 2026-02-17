package com.shreeaircons.service;

import com.shreeaircons.entity.GalleryImage;
import com.shreeaircons.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GalleryService {
    
    private final GalleryRepository galleryRepository;
    private static final String UPLOAD_DIR = "uploads/gallery/";
    
    public GalleryImage uploadImage(GalleryImage image, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);
            
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            Files.write(uploadPath.resolve(fileName), file.getBytes());
            image.setImageUrl("/uploads/gallery/" + fileName);
        }
        
        return galleryRepository.save(image);
    }
    
    public Optional<GalleryImage> getImageById(Long id) {
        return galleryRepository.findById(id);
    }
    
    public List<GalleryImage> getImagesByServiceType(String serviceType) {
        return galleryRepository.findByServiceTypeAndIsActiveTrue(serviceType);
    }
    
    public List<GalleryImage> getAllActiveImages() {
        return galleryRepository.findByIsActiveTrue();
    }
    
    public List<GalleryImage> getAllImages() {
        return galleryRepository.findAll();
    }
    
    public GalleryImage updateImage(Long id, GalleryImage imageDetails) {
        Optional<GalleryImage> optionalImage = galleryRepository.findById(id);
        if (optionalImage.isPresent()) {
            GalleryImage image = optionalImage.get();
            image.setImageDescription(imageDetails.getImageDescription());
            image.setImageType(imageDetails.getImageType());
            image.setIsActive(imageDetails.getIsActive());
            return galleryRepository.save(image);
        }
        throw new RuntimeException("Image not found");
    }
    
    public void deleteImage(Long id) {
        galleryRepository.deleteById(id);
    }
}
