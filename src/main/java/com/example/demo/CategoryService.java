package com.example.demo;

import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long addCategory(String categoryName){
        return categoryRepository.save(
                Category.builder()
                        .categoryName(categoryName)
                        .build()
        ).getId();
    }

    public Category findCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElse(null);
    }

    public Long deleteCategoryById(Long id){
        return null;
    }
}
