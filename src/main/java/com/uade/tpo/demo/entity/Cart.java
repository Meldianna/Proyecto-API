package com.uade.tpo.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Cart")
@Data
@NoArgsConstructor


public class Cart {

    public Cart(User user, LocalDate createdAt){
        this.user = user;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    @OneToMany(mappedBy="cart")
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    @OneToOne
    @JoinColumn(name ="user_id", nullable=true, unique= true)
    private User user;

    @Column
    private LocalDate createdAt;

    //calcular su total
    public Double CalculateTotalPrice(){
        double totalPrice = 0.0;
        if (cartItems.isEmpty())
            return totalPrice;

        for (CartItem item : cartItems){
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    //añadir al carrito
    public void add(Product product, int quantity) {
        // Lógica INTERNA: ¿cómo manejo mis items?
        Optional<CartItem> existingItem = cartItems.stream()
            .filter(item -> item.getProduct().getId().equals(product.getId()))
            .findFirst();
        
        if (existingItem.isPresent()) {
            existingItem.get().incrementQuantity(quantity);
        } else {
            CartItem newItem = new CartItem(this, product, quantity);
            cartItems.add(newItem);
        }
    }



    


    
}
