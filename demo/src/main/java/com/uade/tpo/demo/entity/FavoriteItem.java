package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "favorite-items")
public class FavoriteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favorite_list_id", nullable = false)
    private FavoriteList favoriteList;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    public FavoriteItem() {}

    public FavoriteItem(FavoriteList favoriteList, Product product) {
        this.favoriteList = favoriteList;
        this.product = product;
    }
    
}
