package com.uade.tpo.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.CartItem;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.CartItemRequest;
import com.uade.tpo.demo.entity.dto.CartItemResponse;
import com.uade.tpo.demo.entity.dto.CartResponse;
import com.uade.tpo.demo.exceptions.NoCartForThatUserException;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.CartRepository;
import com.uade.tpo.demo.repository.ProductRepository;
import com.uade.tpo.demo.repository.UserRepository;

import jakarta.transaction.Transactional;



@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository; //servicio porque ya mapea a UserResponse

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CartItemResponse> getAllCartItems(Long userId) throws NoUserIdException{
        Cart cart = this.getCartByUser(userId); 
        return toCartItemResponse(cart.getCartItems(), cart);
    }

    @Override
    public Cart getCartByUser(Long userId)throws NoUserIdException{ //for now, via repository. Then, via token validation
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new NoUserIdException();
        //return cartRepository.findById(user.get().getId()).get(); //retorna la entidad Cart tras buscarlo por el usuario
        //CartResponse cart = user.get().getCartId();
        
        if (user.get().getCart() == null) {
            throw new NoUserIdException();
        }
    
        return user.get().getCart();
    }

    //here. Create a new response, and modify the name of the "request"
    @Override
    @Transactional
    public void addItem(Long userId, CartItemRequest cartItem)throws NoCartForThatUserException, NoSuchProductException{
        
        //validación de productId válido.
        Product existingProduct = productRepository.getProductById(cartItem.getProduct());
        if (existingProduct == null)
            throw new NoSuchProductException();

        //validación que exista un carrito con el usuario
        Optional<Cart> cart = cartRepository.findById(userId);
        if (cart.isEmpty())
            throw new NoCartForThatUserException();

        //añadir al carrito
        cart.get().addItem(existingProduct, cartItem.getQuantity());
        
    }

    @Override
    @Transactional
    public void removeItem(Long userId, Long itemId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("No se encontró carrito para el usuario con ID: " + userId));

        if (cart.getCartItems().contains(itemId))
            cart.getCartItems().remove(itemId);
        
        //    cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));

        //cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new NoCartForThatUserException());
        cart.getCartItems().clear();

        //cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart createCart(User user) {
        //Optional<User> user = userRepository.findById(userId);
        Cart cart = new Cart(user, LocalDate.now());
        return cartRepository.save(cart);
    }

    public CartResponse toCartResponse(Cart cart){
        return new CartResponse(toCartItemResponse(cart.getCartItems(), cart),
        cart.getUser().getId(),
        cart.getCreatedAt());
    }

    public List<CartItemResponse> toCartItemResponse(List<CartItem> items, Cart cart){
        List<CartItemResponse> dtoList = new ArrayList<>();
        items.forEach(item -> {
            //creating the DTO object 
            CartItemResponse dto = new CartItemResponse();
            dto.setCart(cart.getId());
            dto.setProduct(item.getProduct().getId());
            dto.setQuantity(item.getQuantity());

            dtoList.add(dto);
            }
            );
        return dtoList;
    }


    

    

    
}


    

