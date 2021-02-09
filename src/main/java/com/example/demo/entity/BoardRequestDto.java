package com.example.demo.entity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private String user;
    private Long categoryId;

    @Builder
    public BoardRequestDto(String title, String content, String user, Long categoryId) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.categoryId = categoryId;
    }
}
