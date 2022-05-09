package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.BoardInsertDto;
import com.graduate_ok.graduate_ok.dto.BoardListDto;
import com.graduate_ok.graduate_ok.dto.BoardUpdateDto;
import com.graduate_ok.graduate_ok.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardRestController {
    private final BoardService boardService;

    /**
     * 게시글 목록 조회
     * @return
     */
    @GetMapping
    public List<BoardListDto> selectBoardList() {
        return boardService.selectBoardList();
    }

    /**
     * 게시글 조회 + 조회수 증가
     * @param key
     * @return
     */
    @GetMapping("/{key}")
    public List<BoardDto> selectBardByKey(@PathVariable("key") Integer key) {
        boardService.updateLookup(key);
        return boardService.selectBoardByKey(key);
    }

    /**
     * 게시글 작성
     * @param boardInsertDto
     */
    @PostMapping
    public void insertBoard(BoardInsertDto boardInsertDto) {
        boardService.insertBoard(boardInsertDto);
    }

    /**
     * 게시글 수정
     * @param key
     * @param boardUpdateDto
     */
    @PutMapping("/{key}")
    public void updateBoard(@PathVariable("key") Integer key, BoardUpdateDto boardUpdateDto) {
        boardUpdateDto.setBrdKey(key);
        boardService.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 삭제
     * @param key
     */
    @DeleteMapping("/{key}")
    public void deleteBoard(@PathVariable("key") Integer key) {
        boardService.deleteBoard(key);
    }
}
