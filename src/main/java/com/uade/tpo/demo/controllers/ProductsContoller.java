package com.uade.tpo.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.entity.dto.CategoryResponse;
import com.uade.tpo.demo.entity.dto.PriceUpdateRequest;
import com.uade.tpo.demo.entity.dto.ProductRequest;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.NoSuchCategoryException;
import com.uade.tpo.demo.exceptions.NoSuchDiscountException;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.service.CategoryServiceImpl;
import com.uade.tpo.demo.service.ProductServiceImpl;





@RestController
@RequestMapping("products")

public class ProductsContoller {
    @Autowired
   private ProductServiceImpl productService;

   @Autowired
   private CategoryServiceImpl categoryService;

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
     //Uso el DTO para todas las respuestas y requests. Sólo uso las entidades en el repo
     
     @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productService.getAllProducts(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productService.getAllProducts(PageRequest.of(page, size)));
    }

     //buscar por nombre
     @GetMapping("/name/{productName}")
     public ResponseEntity<ProductResponse> getProductByName(@PathVariable String productName)  {
        
            ProductResponse result = productService.getProductByName(productName);
            if (result == null)
                ResponseEntity.badRequest().body("Disculpa, pero no vendemos el producto que buscas.");
            return ResponseEntity.ok(result); //retorna una respuesta con código 200
       

    }

    //  buscar por categoría
    //  @GetMapping("/category/{productCategory}") //o /category/{categoryId}
    //  public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long productCategory) {
    //         List<Product> products = productService.getProductByCategory(productCategory);
    //         //validación para saber si existe la categoría??

    //         if(products.isEmpty())
    //         return ResponseEntity.noContent().build(); //instancia un objeto con BodyBuilder con código 204

    //     return ResponseEntity.ok(products); //retorna una respuesta con código 200
       
    //  }

    @GetMapping("/category/{productCategory}") //o /category/{categoryId}
     public ResponseEntity<Object> getProductByCategory(@PathVariable Long productCategory) {
            Optional<CategoryResponse> existingCategory = categoryService.getCategoryById(productCategory);
            if (existingCategory.isPresent()){
                List<ProductResponse> products = productService.getProductByCategory(productCategory);
                return ResponseEntity.ok(products);
                }
            return ResponseEntity.badRequest().body("No existen productos bajo la categoría que buscas."); //instancia un objeto con BodyBuilder con código 204
     }
     
     //buscar por id
     @GetMapping("/{productId}") //o /search
     public ResponseEntity<Object> getProductById(@PathVariable Long productId){

            ProductResponse result = productService.getProductById(productId);
            if (result == null)
                ResponseEntity.badRequest().body("No existen productos con ese identificador.");
       
            return ResponseEntity.ok(result); //retorna una respuesta con código 200
    }
        
     

     @PostMapping("/create")
     public ResponseEntity<Object> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            ProductResponse result = productService.createProduct(productRequest);
             
            return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);

        } catch (ProductDuplicateException | NoSuchCategoryException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoUserIdException nuie){
            return ResponseEntity.notFound().build();
        }
        
     }


     @DeleteMapping("/delete/{productId}")
     public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchProductException nspe) {
            return ResponseEntity.notFound().build();
        }

        
        
     }
     

     @PutMapping("/put/{productId}")
     public ResponseEntity<Object> updateProductPrice(@PathVariable Long productId, PriceUpdateRequest request) {

        try {
            productService.updateProductPrice(productId, request); //this line throws the exception
            return ResponseEntity.ok("El precio fue actualizado.");
        } 
        catch (NoSuchProductException nspe){
           return ResponseEntity.notFound().build();
        }     
    }

     //adding discounts by its id
     @PostMapping("/{productId}/discount")
     public ResponseEntity<Object> addDiscountById(@PathVariable Long productId, @RequestBody Discount d) {
        try {
            productService.addDiscountById(productId, d);
            return ResponseEntity.ok().body("El descuento se agregó correctamente al producto");

        } catch (NoSuchProductException e) {
            return ResponseEntity.notFound().build();

        }catch (NoSuchDiscountException nsde){
            return ResponseEntity.badRequest().body("El descuento que quiere aplicar no existe.");
        }
         
     }

     //adding discounts by their category
     @PostMapping("/{categoryId}/discount")
        public ResponseEntity<Object> addDiscountByCat(@PathVariable Long categoryId, @RequestBody Discount d) {
            try {
                productService.addDiscountByCat(categoryId, d);
                return ResponseEntity.ok().body("El descuento se agregó correctamente al producto");
    
           
            }catch (NoSuchDiscountException nsde){
                return ResponseEntity.badRequest().body("El descuento que quiere aplicar no existe.");
            }
            catch (NoSuchCategoryException | NoSuchProductException e){
                return ResponseEntity.notFound().build();
            }
     }
     
}
