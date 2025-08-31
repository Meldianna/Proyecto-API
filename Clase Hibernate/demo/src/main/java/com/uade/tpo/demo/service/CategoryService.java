package com.uade.tpo.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.demo.entity.Category;
import com.uade.tpo.demo.entity.dto.CategoryResponse;
import com.uade.tpo.demo.exceptions.CategoryDuplicateException;

public interface CategoryService {

    public Page<Category> getCategories(PageRequest pageRequest);

    public Optional<CategoryResponse> getCategoryById(Long categoryId);

    public Category createCategory(String description) throws CategoryDuplicateException;
}
