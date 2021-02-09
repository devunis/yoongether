package com.example.demo.unit;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    void 카테고리_생성_테스트() throws Exception {
        String givenCategoryName = "example";

        Category givenCategory = Category.builder()
                .id(anyLong())
                .name(givenCategoryName)
                .build();

        String givenCategoryData = objectMapper.writeValueAsString(givenCategory);

        when(categoryService.addCategory(givenCategoryName))
                .thenReturn(givenCategory.getId());

        //then
        mvc.perform(post("/api/category")
                .content(givenCategoryData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(givenCategoryName))
                .andDo(print());

        verify(categoryService, atLeastOnce()).addCategory(givenCategoryName);
    }
    @Test
    void 카테고리_조회_테스트() throws Exception {
        final Long givenId = anyLong();
        final String givenName = "example";
        //when
        when(categoryService.findCategoryById(givenId))
                .thenReturn(Category.builder()
                        .id(givenId)
                        .name(givenName)
                        .build());
        //then
        mvc.perform(get("/api/category/"+givenId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(givenId))
                .andExpect(jsonPath("$.categoryName").value(givenName))
                .andDo(print());

        verify(categoryService, atLeastOnce()).findCategoryById(givenId);
    }
}
