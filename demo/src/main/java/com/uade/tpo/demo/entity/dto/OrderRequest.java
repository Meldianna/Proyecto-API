package com.uade.tpo.demo.entity.dto;


import java.time.LocalDateTime;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data


@AllArgsConstructor

public class OrderRequest {
    private float totalPrice;
    private LocalDateTime date;
    private User userId;
    private DeliveryType deliveryType;
    private PaymentMethod paymentMethod;





}


