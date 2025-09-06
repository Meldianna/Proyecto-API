package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.dto.CategoryResponse;
import com.uade.tpo.demo.exceptions.CategoryDuplicateException;
import com.uade.tpo.demo.exceptions.NoSuchCategoryException;
import com.uade.tpo.demo.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;


   public Page<Category> getCategories(PageRequest pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<CategoryResponse> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
        .map(category -> new CategoryResponse(category.getDescription()));
    }

   public Category createCategory(String description) throws CategoryDuplicateException {
        List<Category> categories = categoryRepository.findByDescription(description); //retorna una lista. Se comprueba si existe la categoría verificando si la lista tiene elementos
        if (categories.isEmpty()) //si está vacía, no hay elementos
        return categoryRepository.save(new Category(description));
    throw new CategoryDuplicateException();
   }


    @Override
    @Transactional
    public void deleteById(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new NoSuchCategoryException();
        }
        categoryRepository.deleteById(id);
    }
    
}
