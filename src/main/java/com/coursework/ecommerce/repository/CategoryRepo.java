package com.coursework.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coursework.ecommerce.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName);

}