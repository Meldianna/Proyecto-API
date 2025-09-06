package com.uade.tpo.demo.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; //API que se incluye para la persistencia
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity //se declara como una entidad que luego el ORM lo mapea como objeto relacional
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    //public Category(){} //para Hybernate, para crear entidades


    public Category(String description){
        this.description = description;
    }

    @Id //se define que el atributo es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @Column
    private String description;

    
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> product;
}
