package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.PriceUpdateRequest;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;

public interface ProductService {
    
    public Page<ProductResponse> getAllProducts(Pageable pageable);
    
    public ProductResponse getProductByName(String name) throws NoSuchProductException;
    public ProductResponse getProductById(@PathVariable Long id) throws NoSuchProductException;
    public List<ProductResponse> getProductByCategory(@PathVariable Long productCategory);

    public Product createProduct(String name, String desc, double price, int stock, Category cat, User owner) throws ProductDuplicateException;

    public void deleteProduct(Long productID) throws  NoSuchProductException;

    public void updateProductPrice(Long id, PriceUpdateRequest request) throws NoSuchProductException;


}
