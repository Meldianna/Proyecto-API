package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;

public interface ProductService {
    
    public Page<Product> getAllProducts(Pageable pageable);
    
    public Product getProductByName(String name) throws NoSuchProductException;
    public Optional<Product> getProductById(@PathVariable Long id) throws NoSuchProductException;
    public List<Product> getProductByCategory(@PathVariable Long productCategory);

    public Product createProduct(String name, String desc, double price, int stock, Category cat, User owner) throws ProductDuplicateException;

    public void deleteProduct(Long productID) throws  NoSuchProductException;

    public void updateProductPrice(Long id, double newAmount) throws NoSuchProductException;


}
