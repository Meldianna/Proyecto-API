package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.FavoriteList;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.ProductInListException;
import com.uade.tpo.demo.exceptions.ResourceNotFoundException;
import com.uade.tpo.demo.repository.FavoriteListRepository;
import com.uade.tpo.demo.repository.ProductRepository;
import com.uade.tpo.demo.repository.UserRepository;

@Service
public class FavotiteListServiceImpl implements FavoriteListService {

    @Autowired
    private FavoriteListRepository favoriteListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public FavoriteList createFavoriteList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setUser(user);
        return favoriteListRepository.save(favoriteList);

    }
    @Override
    public ProductResponse addProductToList(Long listId, Long productId) throws ProductInListException, ResourceNotFoundException{
        Optional<FavoriteList> optionalList = favoriteListRepository.findById(listId);
        if (optionalList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        FavoriteList favoriteList = optionalList.get();
        Set<Product> productsInList = favoriteList.getProducts();
        for (Product existingProduct : productsInList) {
            if (existingProduct.getId().equals(productId)) {
                throw new ProductInListException();
            }
        }
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Product productToAdd = existingProduct.get();

        favoriteList.getProducts().add(productToAdd);
        favoriteListRepository.save(favoriteList);

        return new ProductResponse(productToAdd.getId(), productToAdd.getName(), productToAdd.getPrice(), productToAdd.getStock(), productToAdd.getCategory().getId());
    }

    @Override
    public void deleteProduct(Long listId, Long productId) throws ResourceNotFoundException {
        Optional<FavoriteList> existingList = favoriteListRepository.findById(listId);
        if (existingList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        FavoriteList favoriteList = existingList.get();
        Product productToRemove = null;
        for (Product product : favoriteList.getProducts()) {
            if (product.getId().equals(productId)) {
                productToRemove = product;
                return; 
            }
        }
        if (productToRemove == null) {
            throw new ResourceNotFoundException();
        }
        favoriteList.getProducts().remove(productToRemove);
        favoriteListRepository.save(favoriteList);
    }

    @Override
    public void delete(FavoriteList favoriteList) {
        
    }

    @Override
    public FavoriteList update(FavoriteList favoriteList) {

        return favoriteListRepository.save(favoriteList);
        
    }

    @Override
    public void deleteById(Long id) {
        favoriteListRepository.deleteById(id);
    }

    @Override
    public List<FavoriteList> findByUserId(Long userId) {
        return favoriteListRepository.findAll();

    }

    @Override
    public FavoriteList findById(Long id) {
        return favoriteListRepository.findById(id).orElse(null);
    }
    
}
