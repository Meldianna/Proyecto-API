package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.UserCreateDTO;
import com.uade.tpo.demo.entity.dto.UserResponse;
import com.uade.tpo.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios como UserResponse
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(this::toUserResponse)
                             .collect(Collectors.toList());
    }

    // Obtener usuario por id como UserResponse
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                             .map(this::toUserResponse);
    }

    // Crear usuario desde UserCreateDTO
    public UserResponse createUser(UserCreateDTO userCreateDTO) {
        User user = toEntity(userCreateDTO);
        user = userRepository.save(user);
        return toUserResponse(user);
    }

    // Actualizar usuario desde UserCreateDTO
    public UserResponse updateUser(Long id, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        updateEntity(user, userCreateDTO);
        user = userRepository.save(user);
        return toUserResponse(user);
    }

    // Eliminar usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // --- Métodos privados de conversión ---
    private UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getSurname(),
            user.getPhone_number()
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
