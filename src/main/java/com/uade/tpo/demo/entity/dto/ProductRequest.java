package com.uade.tpo.demo.entity.dto;

import lombok.Data;

@Data
public class ProductRequest {
    //sin el ID porque esto no se debe poner en la base de datos
    //private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
   // private Discount discount;
    private Long ownerId;
}
