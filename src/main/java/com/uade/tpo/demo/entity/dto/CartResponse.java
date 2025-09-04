package com.uade.tpo.demo.entity.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {

    private List<CartItemResponse> items = new ArrayList<>();
    private Long userId;
    private LocalDate createdAt;

    
}
