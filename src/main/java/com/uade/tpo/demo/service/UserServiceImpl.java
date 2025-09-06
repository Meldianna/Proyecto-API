package com.uade.tpo.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.Role;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.UserCreateDTO;
import com.uade.tpo.demo.entity.dto.UserResponse;
import com.uade.tpo.demo.exceptions.DuplicateUserException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.CartRepository;
import com.uade.tpo.demo.repository.FavoriteListRepository;
import com.uade.tpo.demo.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FavoriteListRepository favoriteListRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private CartServiceImpl cartService;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(this::toUserResponse)
                             .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                             .map(this::toUserResponse);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateDTO userCreateDTO) throws DuplicateUserException{

        //validación para no crear dos usuarios con mismo mail (iguales)
        String requestEmail = userCreateDTO.getEmail();

        User user = this.toEntity(userCreateDTO);
        
        user.setActive(true); 
        user.setRole(Role.USER);//creado activo el usuario;
        user = userRepository.save(user);
        //validación temporal
        if (Role.USER.equals(user.getRole())){
            Cart userCart = cartService.createCart(user);
            //userCart = cartRepository.save(userCart);
            user.setCart(userCart);
            user = userRepository.save(user);
        }
        
        return toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        updateEntity(user, userCreateDTO);
        user = userRepository.save(user);
        return toUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) throws NoUserIdException{ //soft drop
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NoUserIdException());
            
        Cart cart = user.getCart();
        if (cart != null) {
            cartRepository.delete(cart);
            user.setCart(null); //rompemos asociación
        }
        List<FavoriteList> userFavoriteList = favoriteListRepository.findByUserId(id);
        if (!userFavoriteList.isEmpty()){
            favoriteListRepository.deleteAll(userFavoriteList); //rompemos asociación
        }
        
        user.setActive(false);
        userRepository.save(user);

    }

    // --- Métodos privados de conversión ---
    private UserResponse toUserResponse(User user) {
        Long cartId = null;
        //si el usuario es ADMIN, tiene cart null.
        //
        if (user.getCart() != null)
            cartId = user.getCart().getId();

        return new UserResponse(
            user.getEmail(),
            user.getName(),
            user.getSurname(),
            user.getPhone_number(),
            cartId
        );
    }

    private User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setPhone_number(dto.getPhoneNumber());
        user.setDate(LocalDateTime.now());
        return user;
    }

    private void updateEntity(User user, UserCreateDTO dto) {
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setPhone_number(dto.getPhoneNumber());
        //user.setDate(dto.getDate());
    }
}