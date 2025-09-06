package com.uade.tpo.demo.entity.dto;


import java.time.LocalDateTime;



import lombok.Data;


@Data
public class OrderRequest {
    private Long cartId;
    private Long userId;
    private Long deliveryType;
    private Long paymentMethod;
   
}


