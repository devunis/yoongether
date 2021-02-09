package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final CategoryService categoryService;
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("category", categoryService.findAllCategory());
        return "index";
    }

    @GetMapping("/category/{id}")
    public String category(Model model, @PathVariable Long id, Pageable pageable){
        model.addAttribute("boards", boardService.findAllBoardByCategoryId(id, pageable));
        model.addAttribute("category", id);
        return "category";
    }
}
