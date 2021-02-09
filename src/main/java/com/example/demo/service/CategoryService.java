package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Category;
import com.example.demo.repo.BoardRepository;
import com.example.demo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long addCategory(String name){
        return categoryRepository.save(
                Category.builder()
                        .name(name)
                        .build()
        ).getId();
    }

    public Category findCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElse(null);
    }

    public Iterable<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Long id, String categoryName){
        categoryRepository.findById(id).ifPresent(
                category -> {
                    category.setName(categoryName);
                    categoryRepository.save(category);
                }
        );
    }

    public void deleteCategoryById(Long id){
        categoryRepository.findById(id).ifPresent(
                category -> {
                    categoryRepository.deleteById(id);
                }
        );
    }
}
