package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.UserCreateDTO;
import com.uade.tpo.demo.entity.dto.UserResponse;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.CartRepository;
import com.uade.tpo.demo.repository.UserRepository;
import com.uade.tpo.demo.repository.FavoriteListRepository;
import com.uade.tpo.demo.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FavoriteListRepository favoriteListRepository;

    private final UserRepository userRepository;

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
    public UserResponse createUser(UserCreateDTO userCreateDTO) {
        User user = toEntity(userCreateDTO);
        user.setActive(true); //creado activo el usuario;
        user = userRepository.save(user);
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
        return new UserResponse(
            user.getEmail(),
            user.getName(),
            user.getSurname(),
            user.getPhone_number(),
            user.getCart().getId()
        );
    }

    private User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setPhone_number(dto.getPhoneNumber());
        user.setDate(dto.getDate());
        return user;
    }

    private void updateEntity(User user, UserCreateDTO dto) {
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setPhone_number(dto.getPhoneNumber());
        user.setDate(dto.getDate());
    }
}