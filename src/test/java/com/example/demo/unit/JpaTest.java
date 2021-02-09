package com.example.demo.unit;

import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
public class JpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 카테고리가_잘_들어가는지(){
        //given
        Category cate = Category.builder()
                .name("test_category")
                .build();

        //when
        entityManager.persist(cate);

        //then
        Category actual = entityManager.find(Category.class, cate.getId());

        assertThat(actual)
                .isNotNull()
                .isEqualTo(cate);
    }

    @Test
    void 카테고리가_잘_조회되는지(){
        Category cate1 = Category.builder()
                .name("cate1")
                .build();
        Category cate2 = Category.builder()
                .name("cate2")
                .build();
        Category cate3 = Category.builder()
                .name("cate3")
                .build();
        Category cate4 = Category.builder()
                .name("cate4")
                .build();

        entityManager.persist(cate1);
        entityManager.persist(cate2);
        entityManager.persist(cate3);
        entityManager.persist(cate4);

        //when
        List<Category> categoryList = categoryRepository.findAll();
        Category actual = categoryRepository.findById(cate3.getId())
                .orElse(null);

        //then
        assertThat(categoryList)
                .hasSize(4);
        assertThat(actual)
                .isNotNull()
                .isEqualTo(cate3);

    }

    @Test
    void 카테고리가_잘_삭제되는지(){
        Category cate1 = Category.builder()
                .name("cate1")
                .build();
        Category cate2 = Category.builder()
                .name("cate2")
                .build();

        entityManager.persist(cate1);
        entityManager.persist(cate2);

        //when
        categoryRepository.deleteAll();

        assertThat(categoryRepository.findAll())
                .isNullOrEmpty();

    }
}
