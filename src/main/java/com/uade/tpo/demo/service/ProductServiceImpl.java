package com.uade.tpo.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.PriceUpdateRequest;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable); //validación si existen productos
    
        if (productsPage.isEmpty()) {
            return Page.empty(pageable); 
        }
        
        return productsPage.map(this::toProductResponse); //mapeo a ProductResponse
    }

    @Override
    public ProductResponse getProductByName(String name) throws NoSuchProductException{
        Product result = productRepository.getProductByName(name);
        if (result == null)
            throw new NoSuchProductException();
        return toProductResponse(result);
    }

    @Override
    public ProductResponse getProductById(Long id) throws NoSuchProductException{
        Product result = productRepository.getProductById(id);
        if (result == null)
            throw new NoSuchProductException();
        return toProductResponse(result);
    }

    @Override
    public List<ProductResponse> getProductByCategory(Long productCategory) {
        return productRepository.getProductByCategory(productCategory)
        .stream()
        .map(this::toProductResponse)
        .collect(Collectors.toList());
    }

    

    @Override
    @Transactional
    public void deleteProduct(Long productID) throws NoSuchProductException {
    
        if (!productRepository.existsById(productID)) {
            throw new NoSuchProductException();
        }
        productRepository.deleteById(productID); //método viene por defecto por el JPA

    }

    @Override
    @Transactional
    public void updateProductPrice(Long id, PriceUpdateRequest request) throws NoSuchProductException {
        Product product = productRepository.getProductById(id);
    
        if (product == null) {
            throw new NoSuchProductException();
        }
    
        double newAmount = request.getNewAmount();
        product.setPrice(newAmount);
    }

    //aceptar el productRequest. Modify
    @Override
    public Product createProduct(String name, String desc, double price, int stock, Category cat, User owner)
            throws ProductDuplicateException {
                Product existingProduct = productRepository.getProductByName(name);
    
                if (existingProduct != null) {  
                    throw new ProductDuplicateException();
                }
                
                
                return productRepository.save(new Product(name, desc, price, stock, cat, owner));
    }

    //metodo para convertir Product a ProductResponse
    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId());
    }
}
