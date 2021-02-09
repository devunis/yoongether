package com.example.demo.unit;

import com.example.demo.service.CategoryService;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void 카테고리가_잘_생성되는지(){
        Long id = 1000L;
        String categoryName = "category";
        Category cate = Category.builder()
                .id(id)
                .name(categoryName)
                .build();

        given(categoryRepository.save(any()))
                .willReturn(cate);
        given(categoryRepository.findById(id))
                .willReturn(Optional.ofNullable(cate));

        //when
        Long actual = categoryService.addCategory(categoryName);

        //then
        assertThat(actual)
                .isNotNull()
                .isEqualTo(id);
        verify(categoryRepository, atLeastOnce()).save(any());

        categoryRepository.findById(id).ifPresent(
                category -> assertEquals(category.getName(), categoryName));
        verify(categoryRepository, atLeastOnce()).findById(id);

    }

    @Test
    void 카테고리가_잘_조회되는지(){
        String categoryName = "category";
        Category category = Category.builder()
                .name(categoryName)
                .build();

        given(categoryRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(category));

        //when
        Category actual = categoryService.findCategoryById(anyLong());

        //then
        assertThat(actual).isNotNull();
        assertEquals(actual.getName(), categoryName);

        verify(categoryRepository,atLeastOnce()).findById(anyLong());
    }

    @Test
    void 카테고리가_잘_수정되는지(){
        Long id = 1000L;
        String categoryName = "example";
        String newName = "newName";

        Category cate = Category.builder()
                .id(id)
                .name(categoryName)
                .build();

        given(categoryRepository.findById(id))
                .willReturn(Optional.ofNullable(cate));

        //when
        categoryService.updateCategory(id,newName);
        //then
        verify(categoryRepository, atLeastOnce()).save(cate);
        assertEquals(newName, cate.getName());
    }

    @Test
    void 카테고리가_잘_삭제되는지(){
        Long id = 1000L;
        String categoryName = "example";

        Category cate = Category.builder()
                .id(id)
                .name(categoryName)
                .build();

        given(categoryRepository.findById(id))
                .willReturn(Optional.ofNullable(cate));

        //when
        categoryService.deleteCategoryById(id);
        //then
        verify(categoryRepository, atLeastOnce()).deleteById(id);


    }
}
