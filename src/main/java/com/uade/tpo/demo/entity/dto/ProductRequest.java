package com.uade.tpo.demo.entity.dto;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Category category;
    private Discount discount;
    private User owner;
}
