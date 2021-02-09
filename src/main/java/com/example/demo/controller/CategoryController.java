package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<Iterable<Category>> findAllCategory(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PostMapping("/api/category")
    public ResponseEntity<Long> addCategory(@RequestBody Category categoryDto){
        return ResponseEntity.ok(categoryService.addCategory(categoryDto.getName()));
    }

    @PutMapping("/api/category/{id}")
    public ResponseEntity<?> updateCategoryName(@PathVariable Long id, @RequestBody Category categoryDto){
        categoryService.updateCategory(id, categoryDto.getName());
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(id);
    }
}
