package com.uade.tpo.demo.entity.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private int stock;
   private Long categoryId;
    //user no porque no queremos saber el usuario que creó el producto

    public ProductResponse(Long id, String name, Double price, int stock, Long productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = productCategory;
    }
}
