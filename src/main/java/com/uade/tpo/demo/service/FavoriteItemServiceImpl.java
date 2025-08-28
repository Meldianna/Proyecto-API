package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.FavoriteItem;
import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.repository.FavoriteItemRepository;
import com.uade.tpo.demo.repository.FavoriteListRepository;
import com.uade.tpo.demo.repository.ProductRepository;

@Service
public class FavoriteItemServiceImpl implements FavoriteItemService {
    @Autowired
    private FavoriteItemRepository favoriteItemRepository;
    private FavoriteListRepository favoriteListRepository;
    private ProductRepository productRepository;
            

    @Override
    public FavoriteItem addProductToFavorites(Long favoriteListId, Long productId) {
         FavoriteList favoriteList = favoriteListRepository.findById(favoriteListId)
                .orElseThrow(() -> new RuntimeException("Favorite list not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setFavoriteList(favoriteList);
        favoriteItem.setProduct(product);

        return favoriteItemRepository.save(favoriteItem);
    }

    @Override
    public List<FavoriteItem> getItemsByFavoriteList(Long favoriteListId) {
        return favoriteItemRepository.findByFavoriteListId(favoriteListId);
    }

    @Override
    public void removeProductFromFavorites(Long itemId) {
        favoriteItemRepository.deleteById(itemId);
    }

}
