package com.uade.tpo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    //búsqueda por igualdad de nombre
    @Query(value ="select p from Product p where p.name = :name AND p.active = true")
    public Product getProductByName(String name);

    @Query(value="select p from Product p where p.id = :id AND p.active = true")
    public Product getProductById(Long id);

    @Query(value="select p from Product p where p.category = :productCategory AND p.active = true")
    public List<Product> getProductByCategory(@Param("productCategory") Long productCategory);

    
}
