package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardRequestDto;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<Long> createBoard(@RequestBody BoardRequestDto boardDto) throws Exception {
        return ResponseEntity.ok(boardService.createBoard(boardDto));
    }

    @GetMapping("/api/board")
    public ResponseEntity<Page<Board>> findAllBoard(Pageable pageable){
        return ResponseEntity.ok(boardService.findAllBoard(pageable));
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity<Board> findBoardById(@PathVariable Long id){
        return ResponseEntity.ok(boardService.findBoardById(id));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<Page<Board>> findBoardsByCategoryId(@PathVariable("id") Long categoryId, Pageable pageable){
        return ResponseEntity.ok(boardService.findAllBoardByCategoryId(categoryId, pageable));
    }
}
