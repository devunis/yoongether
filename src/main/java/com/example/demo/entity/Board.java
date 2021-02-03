package com.example.demo.entity;

import com.example.demo.BaseTimeEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long title;
    private Long content;
    private Long user;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private Category category;

    @Builder
    public Board(Long id, Long title, Long content, Long user, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
    }
}
