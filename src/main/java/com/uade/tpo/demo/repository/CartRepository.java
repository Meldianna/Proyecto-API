package com.uade.tpo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    //@Query("select c from Cart c where c.user = :userId")
    public Optional<Cart> findByUserId(Long userId);
    
}
