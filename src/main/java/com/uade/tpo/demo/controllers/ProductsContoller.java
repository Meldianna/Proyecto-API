package com.uade.tpo.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("products")

public class ProductsContoller {
    //para buscar por categoría. Inyectamos CategoriesController
    @Autowired
    private CategoriesController catController;
    
    /*Operaciones con productos:
     * 1. agregar producto
     * 2. eliminar producto
     * 3. agregar producto a carrito
     * 4. agregar producto a lista de favoritos
     * 5. getters: por nombre, por precio (quizá esto en front??), por categoría
     * 6. 
     * 
     */

     //mostrar todos los productos
     @GetMapping
     public ResponseEntity<List<Product>> getAllProducts() {
         return ResponseEntity.ok(productService.getAllProducts()); //agregar validación: si hay productos...
     }

     //buscar por nombre
     @GetMapping("/{productName}")
     public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        Optional<Product> result = productService.getProductByName(name);
        if (result.isPresent())
            return ResponseEntity.ok(result.get()); //retorna una respuesta con código 200

        return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204
     }

     //buscar por categoría
     @GetMapping("/{productCategory}")
     public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long catId) {
       // ResponseEntity<Category> category = catController.getCategoryById(catId); //searchById
        //if (category){
            List<Product> products = productService.getProductByCategory(catId);
            if(products.isEmpty())
            return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204
        return ResponseEntity.ok(products); //retorna una respuesta con código 200
       
     }



     
     
     
}
