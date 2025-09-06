package com.uade.tpo.demo.repository;

import java.util.List; //libreria de DATA.JPA, trayendo la implementación directa de la API de JPA basada en el framework Hybernate

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{ //primer parámetro: entidad que se consulta
    //segundo parámetro: tipo de dato del id de la entidad (PK)
    //conectividad con la base de datos. Se implementa la API con el JPA, y se establecen las relaciones entre objetos y tablas: JPA
    @Query(value = "select c from Category c where c.description = :description")
    List<Category> findByDescription(String description); //búsqueda directa en la base de datos

    
}

