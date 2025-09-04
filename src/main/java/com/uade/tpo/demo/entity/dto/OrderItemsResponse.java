package com.uade.tpo.demo.entity.dto;

import lombok.Data;

@Data
public class OrderItemsResponse {
    //private Long orderId;
    private Long productId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    
}