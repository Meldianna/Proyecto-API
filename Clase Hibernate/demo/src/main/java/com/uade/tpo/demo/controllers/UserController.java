package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.dto.UserCreateDTO;
import com.uade.tpo.demo.entity.dto.UserResponse;
import com.uade.tpo.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener usuario por id
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Crear usuario
    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,
                                   @RequestBody @Valid UserCreateDTO userCreateDTO) {
        return userService.updateUser(id, userCreateDTO);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}



