package com.example.demo.unit;

import com.example.demo.CategoryService;
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

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void 카테고리가_잘_생성되는지(){
        Long id = 1L;
        String categoryName = "category";
        Category cate = Category.builder()
                .id(id)
                .categoryName(categoryName)
                .build();

        given(categoryRepository.save(any()))
                .willReturn(cate);
        given(categoryRepository.findById(id))
                .willReturn(Optional.ofNullable(cate));

        Long actual = categoryService.addCategory(categoryName);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(id);

        Category actualCategory = categoryRepository.findById(id).get();

        assertEquals(actualCategory.getCategoryName(), categoryName);
    }

    @Test
    void 카테고리가_잘_조회되는지(){
        String categoryName = "category";
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();

        given(categoryRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(category));

        Category actual = categoryService.findCategoryById(anyLong());

        assertThat(actual).isNotNull();
        assertEquals(actual.getCategoryName(), categoryName);
    }

    @Test
    void 카테고리가_잘_삭제되는지(){

    }
}
