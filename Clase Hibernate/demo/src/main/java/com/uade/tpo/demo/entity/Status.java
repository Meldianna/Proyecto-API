package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "status")
public class Status {


    public Status() {}

    
    public Status(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String description;
}
