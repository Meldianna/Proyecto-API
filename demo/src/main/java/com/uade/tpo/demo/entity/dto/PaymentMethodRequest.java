package com.uade.tpo.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodRequest {
    private String description;




    
    public PaymentMethodRequest(String description) {
        this.description = description;
    }
}
