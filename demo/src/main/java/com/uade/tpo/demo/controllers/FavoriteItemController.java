package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.FavoriteItem;
import com.uade.tpo.demo.service.FavoriteItemServiceImpl;

@RestController
@RequestMapping("/favoriteItems")
public class FavoriteItemController {
    
    @Autowired
    private FavoriteItemServiceImpl favoriteItemServiceImpl;

     @PostMapping("/{listId}/{productId}")
    public ResponseEntity<FavoriteItem> addItemToFavoriteList(@PathVariable Long listId, @PathVariable Long productId) {
        FavoriteItem item = favoriteItemServiceImpl.addProductToFavorites(listId, productId);
        return ResponseEntity.ok(item);
    }


    @GetMapping("/list/{listId}")
    public ResponseEntity<List<FavoriteItem>> getItemsByList(@PathVariable Long listId) {
        List<FavoriteItem> items = favoriteItemServiceImpl.getItemsByFavoriteList(listId);
        return ResponseEntity.ok(items);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavoriteItem(@PathVariable Long id) {
        favoriteItemServiceImpl.removeProductFromFavorites(id);
        return ResponseEntity.noContent().build();
    }
    
}
