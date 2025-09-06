package com.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.DeliveryType;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
    boolean existsByDescription(String description);
}
