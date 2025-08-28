package com.uade.tpo.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    public User(String nombre, String email){
        this.name = nombre;
        this.email = email;
        this.role = null;
        this.orders = null;
        this.products = null;
    }

    @Id //se define que el atributo es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @Column
    private String name;

    @Column
    private String email;
    @Column
    private String surname;
    @Column 
    private String password;
    @Column
    private int phone_number;
    @Column
    private LocalDateTime date;

    @OneToMany(mappedBy= "user") //cardinalidad. User es la FK para la relación
    private List<Order> orders;

    /*@ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;*/
   
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role; //FK

    @OneToMany(mappedBy="owner")
    private List<Product> products;


}
