package com.uade.tpo.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cart_items")
@Data
@NoArgsConstructor

public class CartItem {

    public CartItem(Cart cart, Product p, int quantity){
        this.cart = cart;
        this.product = p;
        this.quantity = quantity;
        this.unitPrice = p.getPrice();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name= "cart_id", nullable= false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(nullable=false)
    private int quantity; //of the product

    @Column
    private double unitPrice; //points to the product's price later

    @Column 
    private double totalPrice;

    
    public double getPrice(){
        return totalPrice = unitPrice * quantity;
    }

    public void incrementQuantity(int quantity){
        this.quantity += quantity;
    }

    public void decrementQuantity(int quantity){
        this.quantity -= quantity;
    }







}
