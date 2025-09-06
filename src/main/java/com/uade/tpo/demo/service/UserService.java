package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.demo.entity.dto.UserCreateDTO;
import com.uade.tpo.demo.entity.dto.UserResponse;
import com.uade.tpo.demo.exceptions.NoUserIdException;

public interface UserService {

    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    UserResponse createUser(UserCreateDTO userCreateDTO);

    UserResponse updateUser(Long id, UserCreateDTO userCreateDTO);

    void deleteUser(Long id) throws NoUserIdException;
}




