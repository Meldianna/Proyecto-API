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
    


    @Id
    @Column(nullable = false, unique = true)
    private String description;
    
}
