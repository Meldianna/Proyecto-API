package com.uade.tpo.demo.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class DiscountResponse {
    private Long id;
    private String discountType;
    private double amount;
    private List<Long> productId;
}