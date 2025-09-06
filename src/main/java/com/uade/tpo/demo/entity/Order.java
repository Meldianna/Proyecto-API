package com.uade.tpo.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@NoArgsConstructor
@Table(name = "orders")
@Data
public class Order {

    //primero hay que mapear todos los cartItems a orderItems, obtener el total y asignarlo a la orden.
    public Order(Cart cart, User user, DeliveryType delivery, PaymentMethod paymentMethod){
        this.cart = cart;
        //this.date = date;
        this.user = user;
        this.deliveryType = delivery;
        this.paymentMethod = paymentMethod;
    }

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column
    private double totalPrice;

    @Column
    private LocalDateTime date;

    @ManyToOne 
    @JoinColumn(name = "user_id", nullable = false) //name: nombre de tabla intermedia
    private User user; //FK

    @ManyToOne
    @JoinColumn(name = "delivey_type", nullable = false)
    private DeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy= "order")
    @JsonIgnore
    private List<OrderItems> items = new ArrayList<OrderItems>(); 

    public Double CalculateTotalPrice(){
        for (OrderItems item : items){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }



}
