package com.uade.tpo.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemRequest {
    
    private Long cart; //refering to ids
    private Long product;
    private int quantity;
}
