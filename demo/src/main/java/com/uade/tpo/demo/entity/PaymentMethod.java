package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "payment_method")
public class PaymentMethod {

    public PaymentMethod(String description) {
        this.description = description;
    }
    


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera solo en la DB
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String description;
    
}
