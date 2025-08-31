package com.uade.tpo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

   /* @Query(value ="select p from Product p")
    public Page<ProductResponse> findAll(Pageable pegeable);*/

    //búsqueda por igualdad de nombre
    @Query(value ="select p from Product p where p.name = :name")
    public Product getProductByName(String name);

    @Query(value="select p from Product p where p.id = :id")
    public Product getProductById(Long id);

    @Query(value="select p from Product p where p.category = :productCategory")
    public List<Product> getProductByCategory(@Param("productCategory") Long productCategory);


    
    /*@Modifying
    @Query(value= "delete from p Product p where p.id = productID")
    public int deleteProduct(Long productID);*/











    
    
}
