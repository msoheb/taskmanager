package com.shoyu.taskmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoyu.taskmanager.entity.Category;
import com.shoyu.taskmanager.exception.CategoryAlreadyExistsException;
import com.shoyu.taskmanager.exception.CategoryNotFoundException;
import com.shoyu.taskmanager.repository.CategoryRepository;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {

        Optional<Category> foundByName = categoryRepository.findByName(category.getName());
        if(foundByName.isPresent()) throw new CategoryAlreadyExistsException("Category already exist");

        return categoryRepository.save(category);

    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with NAME: " + name));
    }

    public Category updateCategory(Long id, Category updatedCategory) {

        Category savedCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
        
        Optional<Category> foundByName = categoryRepository.findByName(updatedCategory.getName());
        if(foundByName.isPresent()  && !foundByName.get().getId().equals(id) ) throw new CategoryAlreadyExistsException("Category already exist");
        
        savedCategory.setName(updatedCategory.getName());
        savedCategory.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(savedCategory);

    }

    public void deleteCategory(Long id) {

        Category savedCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));

        categoryRepository.delete(savedCategory);

    }
    
}
