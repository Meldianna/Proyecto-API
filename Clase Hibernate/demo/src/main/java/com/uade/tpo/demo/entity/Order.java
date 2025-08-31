package com.uade.tpo.demo.entity;

import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Data
public class Order {

    public Order(double totalPrice, LocalDateTime date, User user, DeliveryType delivery, PaymentMethod paymentMethod){
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
        this.deliveryType = delivery;
        this.paymentMethod = paymentMethod;
    }

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

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

    // @ManyToOne
    // @JoinColumn(name = "status_id", nullable = false)
    // private Status status;

}
