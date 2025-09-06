package com.uade.tpo.demo.entity.dto;

import lombok.Data;


@Data
public class CartItemResponse {

    private Long cart; //refering to ids
    private Long product;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
}
