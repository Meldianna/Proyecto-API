package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.ProductInListException;
import com.uade.tpo.demo.exceptions.ResourceNotFoundException;

public interface FavoriteListService {

    ProductResponse addProductToList(Long listId, Long productId) throws ProductInListException, ResourceNotFoundException;

    void deleteProduct(Long listId, Long productId) throws ResourceNotFoundException;

    FavoriteList createFavoriteList(Long userId);

    List<FavoriteList> findByUserId(Long userId);

    void delete(FavoriteList favoriteList);

    FavoriteList update(FavoriteList favoriteList);

    void deleteById(Long id);
    
    FavoriteList findById(Long id);
    
}