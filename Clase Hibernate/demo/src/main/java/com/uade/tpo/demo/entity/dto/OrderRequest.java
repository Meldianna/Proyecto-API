package com.uade.tpo.demo.entity.dto;


import java.time.LocalDateTime;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;

import lombok.Data;


@Data


public class OrderRequest {
    //private int id;
    private double totalPrice;
    private LocalDateTime date;
    private User userId;
    //private Status status;
    private DeliveryType deliveryType;
    private PaymentMethod paymentMethod;
    //agregar producto
    //lista del carrito
}


