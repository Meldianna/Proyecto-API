package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.CartItemRequest;
import com.uade.tpo.demo.entity.dto.CartItemResponse;
import com.uade.tpo.demo.entity.dto.CartResponse;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.UserRepository;
import com.uade.tpo.demo.service.CartServiceImpl;

import jakarta.transaction.Transactional;




@RestController
@RequestMapping("api/cart")

public class CartController {
    //private final int maxQuantityOfItems = 15; 
    //private final int maxQuantityPerProduct = 5;

    //endpoints temporales


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/items/{userId}")
    //nosuchcartid
    public ResponseEntity<Object> findAllCartItems(@PathVariable Long userId) { //se busca el carrito y sus items por usuario
        try{List<CartItemResponse> items = cartService.getAllCartItems(userId);

            return ResponseEntity.ok().body(items);
        
        } catch(NoUserIdException nuie){
            return  ResponseEntity.badRequest().body("No se encuentra un carrito para el usuario");
        }
        
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Object> getTotalCart(@PathVariable Long userId) {
        try {
            Cart cart = cartService.getCartByUser(userId);
        if (cart.getCartItems().isEmpty()) //en realidad esto ya se valida en el método
            return ResponseEntity.badRequest().body("El carrito está vacío.");
        //hacer validación con tokens
        double totalPrice = cart.CalculateTotalPrice();
        return ResponseEntity.ok().body(totalPrice);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encuentra un carrito para el usuario");
        }
        

    }
    
    @PutMapping("/addItem/{useId}/")
    public ResponseEntity<Object> addToCart(@PathVariable Long userId, CartItemRequest cartItem) {
        cartService.addItem(userId, cartItem);
        return ResponseEntity.ok().body("Se añadió el producto al carrito!");
        //add exceptions
    }

    @PutMapping("removeItem/{userId}/{itemId}") 
    public ResponseEntity<Object> removeFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.removeItem(userId, itemId);
        return ResponseEntity.ok().body("Se añadió el producto al carrito!");
        //add exceptions
    }

    @PostMapping
    @Transactional
    //the cart is created right after the user created their account
    public ResponseEntity<CartResponse> createCart(User user) {
        Cart cart = cartService.createCart(user);
        CartResponse cartDTO = cartService.toCartResponse(cart);
        return ResponseEntity.ok().body(cartDTO); 

    }
    

    


    
    
}
