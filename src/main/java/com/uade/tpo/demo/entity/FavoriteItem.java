package com.uade.tpo.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "favorite-items")
@NoArgsConstructor
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


    public FavoriteItem(FavoriteList favoriteList, Product product) {
        this.favoriteList = favoriteList;
        this.product = product;
    }
    
}
