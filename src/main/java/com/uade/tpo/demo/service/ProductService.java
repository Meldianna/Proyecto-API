package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.entity.dto.PriceUpdateRequest;
import com.uade.tpo.demo.entity.dto.ProductRequest;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.NoSuchCategoryException;
import com.uade.tpo.demo.exceptions.NoSuchDiscountException;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;

public interface ProductService {
    
    public Page<ProductResponse> getAllProducts(Pageable pageable);
    
    public ProductResponse getProductByName(String name) throws NoSuchProductException;
    public ProductResponse getProductById(@PathVariable Long id) throws NoSuchProductException;
    public List<ProductResponse> getProductByCategory(@PathVariable Long productCategory);

    public ProductResponse createProduct(ProductRequest productRequest) throws ProductDuplicateException, NoSuchCategoryException, NoUserIdException;

    public void deleteProduct(Long productID) throws  NoSuchProductException;

    public void updateProductPrice(Long id, PriceUpdateRequest request) throws NoSuchProductException;

    public void addDiscountById(Long id, Discount d) throws  NoSuchProductException, NoSuchDiscountException;

    public void addDiscountByCat(Long categoryId, Discount d) throws NoSuchCategoryException, NoSuchDiscountException;


}
