package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.CartItemRequest;
import com.uade.tpo.demo.entity.dto.CartItemResponse;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.NoUserIdException;


public interface CartService {

    public List<CartItemResponse> getAllCartItems(Long userId) throws NoUserIdException;
    public Cart getCartByUser(Long userId) throws NoUserIdException;
    public void addItem(Long userId, CartItemRequest cartItem) throws NoUserIdException,  NoSuchProductException;
    public void removeItem(Long userId, Long itemId);
    public Cart createCart(User user);
    public void clearCart(Long userId);

    
}
