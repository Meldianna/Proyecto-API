package com.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.demo.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

    
} 
