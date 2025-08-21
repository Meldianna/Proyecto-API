package com.uade.tpo.demo.entity;



import jakarta.persistence.Column; //API que se incluye para la persistencia
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity //se declara como una entidad que luego el ORM lo mapea como objeto relacional
public class Category {
    public Category(){} //para Hybernate, para crear entidades

    public Category(String description){
        this.description = description;
    }
    @Id //se define que el atributo es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme a un modelo de datos relacional, se utiliza la estrategia para definir el valor como "autogenerado"
    private Long id;

    @Column
    private String description;

    @OneToOne(mappedBy = "category")
    private Product product;
}
