package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.service.FavotiteListServiceImpl;

@RestController
@RequestMapping("/favoriteList")
public class FavoriteListController {

    //la lista maneja todo: productos, alta y baja
    //items se saca
    //la relación entre lista y producto N:1

    
    @Autowired
    private FavotiteListServiceImpl favoriteListServiceImpl;

    

    // @GetMapping("/{id}")
    // public ResponseEntity<FavoriteList> getFavoriteListById(@PathVariable Long id) {
    //     FavoriteList favoriteList = favoriteListServiceImpl.findById(id);
    //     if (favoriteList != null) {
    //         return ResponseEntity.ok(favoriteList);
    //     }
    //     return ResponseEntity.notFound().build();
    // }

     @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteList>> getFavoriteListsByUser(@PathVariable Long userId) {
        List<FavoriteList> lists = favoriteListServiceImpl.findByUserId(userId);
        return ResponseEntity.ok(lists);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<FavoriteList> createFavoriteList(@PathVariable Long userId) {
        FavoriteList favoriteList = favoriteListServiceImpl.createFavoriteList(userId);
        return ResponseEntity.ok(favoriteList);
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<FavoriteList> updateFavoriteList(@PathVariable Long id,@RequestBody FavoriteList favoriteList) {
    //     favoriteList.setId(id); 
    //     FavoriteList updated = favoriteListServiceImpl.update(favoriteList);
    //     return ResponseEntity.ok(updated);
    // }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<Void> deleteFavoriteList(@PathVariable Long id) {
    //     favoriteListServiceImpl.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }

}
