package com.uade.tpo.demo.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {
    //private Long id;
    private String email;
    private String name;
    private String surname;
    private Long phoneNumber;
    private Long cartId;
    //private LocalDateTime date;
}

