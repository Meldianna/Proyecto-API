package com.uade.tpo.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.sql.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "discount")
@NoArgsConstructor
public class Discount {


    public Discount(int amount, String desc){
        this.amount = amount;
        this.discountType= desc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(value = "Amount")
    private int amount;

    @Column(value="Type of discount")
    private String discountType;

    @OneToMany(mappedBy="discount")
    @JsonIgnore
    private List<Product> product;

    
}
