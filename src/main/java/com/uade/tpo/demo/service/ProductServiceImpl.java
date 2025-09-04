package com.uade.tpo.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.entity.Product;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.PriceUpdateRequest;
import com.uade.tpo.demo.entity.dto.ProductRequest;
import com.uade.tpo.demo.entity.dto.ProductResponse;
import com.uade.tpo.demo.exceptions.NoSuchCategoryException;
import com.uade.tpo.demo.exceptions.NoSuchDiscountException;
import com.uade.tpo.demo.exceptions.NoSuchProductException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.exceptions.ProductDuplicateException;
import com.uade.tpo.demo.repository.CategoryRepository;
import com.uade.tpo.demo.repository.DiscountRepository;
import com.uade.tpo.demo.repository.ProductRepository;
import com.uade.tpo.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    //service or repo??
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CategoryRepository catRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) { //devuelve todos: activos e inactivos
        Page<Product> productsPage = productRepository.findAll(pageable); //validación si existen productos
    
        if (productsPage.isEmpty()) {
            return Page.empty(pageable); 
        }
        
        return productsPage.map(this::toProductResponse); //mapeo a ProductResponse
    }

    @Override
    public ProductResponse getProductByName(String name) throws NoSuchProductException{ //devuelve todos: activos e inactivos
        Product result = productRepository.getProductByName(name);
        if (result == null)
            throw new NoSuchProductException();
        return toProductResponse(result);
    }

    @Override
    public ProductResponse getProductById(Long id) throws NoSuchProductException{
        Product result = this.findByActiveProducts(id); //llamamos a métod privado
        return toProductResponse(result);
    }

    @Override
    public List<ProductResponse> getProductByCategory(Long productCategory) { //devuelve todos: activos e inactivos
        return productRepository.getProductByCategory(productCategory)
        .stream()
        .map(this::toProductResponse)
        .collect(Collectors.toList());
    }

     //metodo para convertir Product a ProductResponse
     public ProductResponse toProductResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCategory().getId());
    }

    @Override
    @Transactional
    public void deleteProduct(Long productID) throws NoSuchProductException {
        Product producToDelete = this.findByActiveProducts(productID); //método privado
        producToDelete.setActive(false);
        productRepository.save(producToDelete);
    }

    @Override
    @Transactional
    public void updateProductPrice(Long id, PriceUpdateRequest request) throws NoSuchProductException {
        Product product = this.findByActiveProducts(id); //llamamos al método privado
        double newAmount = request.getNewAmount();
        product.setPrice(newAmount);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest)
            throws ProductDuplicateException, NoSuchCategoryException, NoUserIdException {
                Product existingProduct = productRepository.getProductByName(productRequest.getName());
    
                if (existingProduct != null) {  
                    throw new ProductDuplicateException();
                }
                Category categoryRef = catRepository.findById(productRequest.getCategoryId())
                .orElseThrow(()  -> new NoSuchCategoryException()); 

                User userRef = userRepository.findById(productRequest.getOwnerId())
                .orElseThrow(() -> new NoUserIdException());

                Product newProduct = new Product( //creamos antes el producto para config su estado
                    productRequest.getName(), 
                    productRequest.getDescription(), 
                    productRequest.getPrice(), 
                    productRequest.getStock(),
                    categoryRef, 
                    userRef
                );

                newProduct.setActive(true); //config boolean to "true"
                Product productCreated =  productRepository.save(newProduct);
                return toProductResponse(productCreated);
    }

    @Override
    @Transactional
    public void addDiscountById(Long productId, Discount d) throws NoSuchProductException, NoSuchDiscountException{
        Discount existingDiscount = discountRepository.findById(d.getId())
        .orElseThrow(() ->  new NoSuchDiscountException());
        
        Product existingProduct = this.findByActiveProducts(productId); //llamamos a método privado

        existingProduct.setDiscount(d);
    }

    @Override
    @Transactional
    public void addDiscountByCat(Long categoryId, Discount d) throws NoSuchCategoryException, NoSuchDiscountException{
        Discount existingDiscount = discountRepository.findById(d.getId())
        .orElseThrow(() ->  new NoSuchDiscountException());
        
        
        Category existingCategory = catRepository.findById(categoryId)
        .orElseThrow(() -> new NoSuchCategoryException());

        List<ProductResponse> products = this.getProductByCategory(categoryId);
        products.forEach(product -> product.setDiscount(d)); //adding the discount to each product
        
    }

    //----método de validación de producto (si es activo)----
    private Product findByActiveProducts(Long productId) throws NoSuchProductException{
        return productRepository.findById(productId).filter(Product::isActive).orElseThrow(() -> new NoSuchCategoryException());

    }

   
}
