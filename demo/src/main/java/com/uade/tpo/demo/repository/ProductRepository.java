package com.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.demo.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {
    
}
