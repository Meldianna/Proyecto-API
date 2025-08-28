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
import java.time.LocalDateTime;



@Entity
@Data
@Table(name = "orders")
public class Order {


    public Order() {
    }

    public Order(float totalPrice, LocalDateTime date, User user, Status status, DeliveryType deliveryType,
            PaymentMethod paymentMethod) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.user = user;
        this.status = status;
        this.deliveryType = deliveryType;
        this.paymentMethod = paymentMethod;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float totalPrice;

    @Column
    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "delivey_type", nullable = false)
    private DeliveryType deliveryType;

    @ManyToOne
    @JoinColumn(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;


}
