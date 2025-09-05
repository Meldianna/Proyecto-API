package com.uade.tpo.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    // public User(String nombre, String email){
    //     this.name = nombre;
    //     this.email = email;
    //     //this.role = null;
    //     this.orders = null;
    //     this.products = null;
    // }

    @Id //se define que el atributo es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @Column
    private String name;

    @Column(unique=true)
    private String email;
    @Column
    private String surname;
    @Column 
    private String password;
    @Column
    private Long phone_number;
    @Column
    private LocalDateTime date;

    @Column
    private Boolean active;

    @OneToMany(mappedBy= "user") //cardinalidad. User es la FK para la relación
    private List<Order> orders;
   
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; 

    //Representa todos los productos que crea el administrador/vendedor
    @OneToMany(mappedBy="owner")
    @JsonIgnore
    private List<Product> products;

    @OneToOne(mappedBy="user", optional=true)
    private Cart cart;


}
