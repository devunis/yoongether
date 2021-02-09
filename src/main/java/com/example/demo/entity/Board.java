package com.example.demo.entity;

import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String user;

    @ManyToOne
    @JoinColumn(columnDefinition = "category_id", nullable = false)
    private Category category;

    @Builder
    public Board(Long id, String title, String content, String user, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
    }
}
