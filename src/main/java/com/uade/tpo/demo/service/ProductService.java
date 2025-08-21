package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;

public interface ProductService {
    public List<Product> getAllProducts();
    public List<Product> getProductByName(String name);
    public Optional<Product> getProductById(@PathVariable Long id) throws NoSuchProductException;
    public List<Product> getProductByCategory(@PathVariable Long id);

    public Product createProduct(String name, String desc, double price, int stock, Category cat, Discount d, User owner) throws ProductDuplicateException;

    public ResponseEntity<Object> deleteProduct(Long productID) throws  NoSuchProductException;

    public ResponseEntity<Object> updateProductPrice(Long id, double newAmount) throws NoSuchProductException;
}
