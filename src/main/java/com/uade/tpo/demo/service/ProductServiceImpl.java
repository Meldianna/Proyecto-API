package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(Pageable pegeable) {
       return productRepository.findAll(pegeable); //validación si existen productos
    }

    @Override
    public Product getProductByName(String name) throws NoSuchProductException{
        Product result = productRepository.getProductByName(name);
        if (result == null)
            throw new NoSuchProductException();
        return result;
    }

    @Override
    public Optional<Product> getProductById(Long id) throws NoSuchProductException{
        Optional<Product> result = productRepository.getProductById(id);
        if (result.isEmpty())
            throw new NoSuchProductException();
        return result;
    }

    @Override
    public List<Product> getProductByCategory(Long productCategory) {
        return productRepository.getProductByCategory(productCategory);
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
    public void updateProductPrice(Long id, double newAmount) throws NoSuchProductException {
        Optional<Product> productOptional = productRepository.getProductById(id);
    
        if (productOptional.isEmpty()) {
            throw new NoSuchProductException();
        }
    
        Product product = productOptional.get();
        product.setPrice(newAmount);
    }

    @Override
    public Product createProduct(String name, String desc, double price, int stock, Category cat, User owner)
            throws ProductDuplicateException {
                Product existingProduct = productRepository.getProductByName(name);
    
                if (existingProduct != null) {  
                    throw new ProductDuplicateException();
                }
                
                
                return productRepository.save(new Product(name, desc, price, stock, cat, owner));
    }
}
