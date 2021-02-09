package com.example.demo.unit;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardRequestDto;
import com.example.demo.repo.BoardRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void 게시글이_잘_생성되는지() throws Exception {

        Board givenBoard = Board.builder()
                .title("title")
                .content("content")
                .build();

        given(boardRepository.save(any()))
                .willReturn(givenBoard);

        boardService.createBoard(
                BoardRequestDto.builder()
                        .title("title")
                        .content("content")
                        .categoryId(1L)
                        .build());

        verify(boardRepository, atLeastOnce()).save(any());

    }
}
