package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="order_items")
public class OrderItems {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable= false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable= false)
    private Product product;

    @Column(nullable=false)
    private int quantity;

    @Column
    private double unitPrice;

    @Column
    private double totalPrice; //total price per product: price * quantity



    public OrderItems(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = this.product.getPrice() * this.quantity;
    }




}
