package com.coursework.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coursework.ecommerce.model.Category;
import com.coursework.ecommerce.repository.CategoryRepo;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepo categoryrepository;

    public CategoryService(CategoryRepo categoryrepository) {
        this.categoryrepository = categoryrepository;
    }

    public List<Category> listCategories() {
        return categoryrepository.findAll();
    }

    public void createCategory(Category category) {
        categoryrepository.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryrepository.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryrepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryID, Category newCategory) {
        Category category = categoryrepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setProducts(newCategory.getProducts());
        category.setImageUrl(newCategory.getImageUrl());

        categoryrepository.save(category);
    }
}