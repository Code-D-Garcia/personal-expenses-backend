package com.codedgarcia.expenses.manager.category.service;

import com.codedgarcia.expenses.manager.category.dto.CategoryResponse;
import com.codedgarcia.expenses.manager.category.dto.CreateCategoryRequest;
import com.codedgarcia.expenses.manager.category.dto.UpdateCategoryRequest;
import com.codedgarcia.expenses.manager.category.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(
            Long userId,
            CreateCategoryRequest request);

    List<CategoryResponse> getAllCategories(Long userId);

    Page<CategoryResponse> getAllCategories(Long userId, Pageable pageable);

    List<CategoryResponse> getCategoriesByType(Long userId,Type type);

    Page<CategoryResponse> getCategoriesByType(Long userId, Type type, Pageable pageable);

    CategoryResponse getCategory(Long userId, Long categoryId);

    CategoryResponse updateCategory(
            Long userId,
            Long categoryId,
            UpdateCategoryRequest request);

    void deleteCategory(Long userId, Long categoryId);
}

