package com.shreeaircons.repository;

import com.shreeaircons.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryImage, Long> {
    List<GalleryImage> findByServiceTypeAndIsActiveTrue(String serviceType);
    List<GalleryImage> findByIsActiveTrue();
}
