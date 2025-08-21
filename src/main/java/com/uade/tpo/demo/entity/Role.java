package com.uade.tpo.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Role {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @Column
    private String description;

   /* @ManyToMany(mappedBy = "roles")
    private List<User> users;*/

    @OneToMany(mappedBy= "role_id")
    private List<User> users; //FK de users, donde cada rol puede tener muchos usuarios asociados

}
