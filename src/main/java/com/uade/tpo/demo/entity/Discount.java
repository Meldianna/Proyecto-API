package com.uade.tpo.demo.entity;

import com.querydsl.sql.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Discount {

    public Discount(){}

    public Discount(int amount, String desc, Product product){
        this.amount = amount;
        this.description = desc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(value = "Amount")
    private int amount;

    @Column(value = "Description")
    private String description;

    
}
