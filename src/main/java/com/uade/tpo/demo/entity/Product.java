package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Product {
    
    public Product(){}

    public Product(String name, String description, double price, int stock, Category cat){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = cat;
        this.discount = null; //inicializa en null
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int stock;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    //OneToOne with Discount
    @Column
    private Discount discount;

    @Column
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User owner;

    
}
