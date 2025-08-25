package com.uade.tpo.demo.entity.dto;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.User;

import lombok.Data;

@Data
public class ProductRequest {
    //sin el ID porque esto no se debe poner en la base de datos
    //private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Category category;
   // private Discount discount;
    private User owner;
}
