package com.uade.tpo.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.uade.tpo.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

   /* @Query(value ="select p from Product p")
    public Page<ProductResponse> findAll(Pageable pegeable);*/

    //búsqueda por igualdad de nombre
    @Query(value ="select p from Product p where p.name = :name")
    public Product getProductByName(String name);

    @Query(value="select p from Product p where p.id = :id")
    public Optional<Product> getProductById(Long id);

    @Query(value="select p from Product p where p.category = :productCategory")
    public List<Product> getProductByCategory(@RequestParam("productCategory") Long id);


    
    /*@Modifying
    @Query(value= "delete from p Product p where p.id = productID")
    public int deleteProduct(Long productID);*/











    
    
}
