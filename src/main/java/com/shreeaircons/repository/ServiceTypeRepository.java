package com.shreeaircons.repository;

import com.shreeaircons.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    Optional<ServiceType> findByName(String name);
    List<ServiceType> findByIsActiveTrueOrderByDisplayOrderAsc();
}
