package com.uade.tpo.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String discountType;

    @Column
    private double amount;

    @OneToOne
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    private Product product;

    public Discount() {}

    public Discount(String discountType, double amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    
}
