package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="Product")
public class Product {

    public Product(String name, String description, double price, int stock, Category cat, User owner){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = cat;
        this.discount = null; //inicializa en null
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true) //nombre único
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    //OneToOne with Discount


    @OneToMany(mappedBy="product")
    @JoinColumn(name="discount_id", referencedColumnName="id")
    private Discount discount;

 
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User owner;

    
}
