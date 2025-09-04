package com.uade.tpo.demo.entity.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private double totalPrice;
    private LocalDateTime date;
    private User userId;
    private DeliveryType deliveryType;
    private PaymentMethod paymentMethod;
    private List<OrderItemsResponse> orderItems;


    



    // public OrderResponse(long id, double totalPrice, LocalDateTime date, User userId,
    //         DeliveryType deliveryType, PaymentMethod paymentMethod) {
    //     this.id = id;
    //     this.totalPrice = totalPrice;
    //     this.date = date;
    //     this.userId = userId;
    //     this.deliveryType = deliveryType;
    //     this.paymentMethod = paymentMethod;
    // }


}

