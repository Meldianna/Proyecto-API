package com.uade.tpo.demo.entity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DiscountRequest {
    private int amount;
    private String discountType;
    private List<Long> productId;
}
