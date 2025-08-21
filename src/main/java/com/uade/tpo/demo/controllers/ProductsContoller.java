package com.uade.tpo.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.entity.dto.ProductRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.service.CategoryServiceImpl;
import com.uade.tpo.demo.service.ProductService;

import org.springframework.web.bind.annotation.PutMapping;

import com.uade.tpo.demo.service.ProductServiceImpl;





@RestController
@RequestMapping("products")

public class ProductsContoller {
    @Autowired
   private ProductServiceImpl productService;

    public ProductsContoller() {
    }
    
    /*Operaciones con productos:
     * 1. agregar producto. check
     * 2. eliminar producto. check
     * 3. agregar producto a carrito --pending
     * 4. agregar producto a lista de favoritos --pending
     * 5. getters: por nombre, por precio (quizá esto en front??), por categoría. check
     * 6. modificar precio del producto.
     * 
     */

     //mostrar todos los productos
     @GetMapping
     public ResponseEntity<List<Product>> getAllProducts() {
         return ResponseEntity.ok(productService.getAllProducts()); //agregar validación: si hay productos...
     }

     //buscar por nombre
     @GetMapping("/{productName}")
     public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) throws NoSuchProductException {
       List<Product> result = productService.getProductByName(name);
        if (result.isEmpty())
            return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204
            
        return ResponseEntity.ok(result); //retorna una respuesta con código 200
        
       
    }

     //buscar por categoría
     @GetMapping("/{productCategory}") //o /category/{categoryId}
     public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long catId) {
       // ResponseEntity<Category> category = catController.getCategoryById(catId); //searchById
        //if (category){
            List<Product> products = productService.getProductByCategory(catId);
            //validación para saber si existe la categoría??

            if(products.isEmpty())
            return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204

        return ResponseEntity.ok(products); //retorna una respuesta con código 200
       
     }

     //buscar por id
     @GetMapping("/{productId}") //o /search
     public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> result = productService.getProductById(id);
        if (result.isPresent())
            return ResponseEntity.ok(result.get()); //retorna una respuesta con código 200

        return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204
    }
        
     

     @PostMapping
     public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) {
        try{ 
            Product result = productService.createProduct(productRequest.getName(), //this should throw the exception
            productRequest.getDescription(),
            productRequest.getPrice(),
            productRequest.getStock(),
            productRequest.getCategory(),
            productRequest.getDiscount(),
            productRequest.getOwner());
             
            return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
        } catch(ProductDuplicateException pde){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
     }


     @DeleteMapping("/{productId}")
     public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        try {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
        }catch(NoSuchProductException nspe){
            return ResponseEntity.notFound().build();
        }

     }
     

     @PutMapping("/{productId}")
     public ResponseEntity<Object> updateProductPrice(@PathVariable Long id, double newAmount) {
         try{
            ResponseEntity<Product> result = getProductById(id);
            result.getBody().setPrice(newAmount);
            return ResponseEntity.ok("El precio fue actualizado.");
         }
         catch (NoSuchProductException nspe){
            return ResponseEntity.notFound().build();
         }         
         
     }

}
