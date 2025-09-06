package com.uade.tpo.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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


    @ManyToOne
    @JoinColumn(name="discount_id", referencedColumnName="id")
    private Discount discount;

 
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User owner;

    @ManyToMany(mappedBy = "products") // "products" es el nombre del campo en FavoriteList
    private Set<FavoriteList> favoriteLists = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Se asegura de que o no sea nulo y que sea una instancia de Product
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        // Dos productos son iguales si y solo si sus IDs no son nulos y son iguales.
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        // Usar getClass().hashCode() es una estrategia segura para entidades.
        // Asegura que el hashCode no cambie una vez que se le asigna un ID a la entidad.
        return getClass().hashCode();
    }
}
