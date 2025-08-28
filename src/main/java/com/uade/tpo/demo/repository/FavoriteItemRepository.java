package com.uade.tpo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.demo.entity.FavoriteItem;

public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, Long> {
        List<FavoriteItem> findByFavoriteListId(Long favoriteListId);

}
