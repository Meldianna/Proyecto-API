package com.uade.tpo.demo.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import com.uade.tpo.demo.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    
    @Query(value = "select p from PaymentMethod p where p.description = ?1")
    PaymentMethod findByDescription(String description);


}
