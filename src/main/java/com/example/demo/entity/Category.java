package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
