package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardRequestDto;
import com.example.demo.entity.Category;
import com.example.demo.repo.BoardRepository;
import com.example.demo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    public Long createBoard(BoardRequestDto boardDto) throws Exception {
        Category category = categoryRepository.findById(boardDto.getCategoryId())
                .orElseThrow(Exception::new);

        return boardRepository.save(
                Board.builder()
                        .title(boardDto.getTitle())
                        .content(boardDto.getContent())
                        .category(category)
                        .user(boardDto.getUser())
                        .build()
        ).getId();

    }

    public Board findBoardById(Long id){
        return boardRepository.findById(id)
                .orElse(null);
    }

    public Page<Board> findAllBoard(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public void editBoard(Long id, Board boardDto){
        boardRepository.findById(id)
                .ifPresent(
                        board -> {
                            board.setTitle(boardDto.getTitle());
                            board.setContent(boardDto.getContent());
                            boardRepository.save(board);
                        }
                );
    }

    public Page<Board> findAllBoardByCategoryId(Long id, Pageable pageable) {
        return boardRepository.findAllByCategory(
                categoryRepository.findById(id).orElse(null)
                ,pageable
        );
    }
}
