package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.FavoriteItem;

public interface FavoriteItemService {

    FavoriteItem addProductToFavorites(Long favoriteListId, Long productId);
    List<FavoriteItem> getItemsByFavoriteList(Long favoriteListId);
    void removeProductFromFavorites(Long itemId);
    
}
