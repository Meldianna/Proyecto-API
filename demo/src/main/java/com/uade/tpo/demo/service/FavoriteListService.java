package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.FavoriteList;

public interface FavoriteListService {

    FavoriteList createFavoriteList(Long userId);

    List<FavoriteList> findByUserId(Long userId);

    void delete(FavoriteList favoriteList);

    FavoriteList update(FavoriteList favoriteList);

    void deleteById(Long id);
    
    FavoriteList findById(Long id);
    
}