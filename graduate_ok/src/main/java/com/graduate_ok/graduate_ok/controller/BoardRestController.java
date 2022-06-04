package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.BoardInsertDto;
import com.graduate_ok.graduate_ok.dto.BoardListDto;
import com.graduate_ok.graduate_ok.dto.BoardUpdateDto;
import com.graduate_ok.graduate_ok.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Board")
@ApiResponses({
        @ApiResponse(code=200, message="ok"),
        @ApiResponse(code=500, message="server error"),
        @ApiResponse(code=404, message="not found")
})
public class BoardRestController {
    private final BoardService boardService;

    /**
     * 게시글 목록 조회
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "게시판 목록 조회 API")
    public List<BoardListDto> selectBoardList() {
        return boardService.selectBoardList();
    }

    /**
     * 게시글 조회 + 조회수 증가
     * @param key
     * @return
     */
    @GetMapping("/{key}")
    @ApiOperation(value = "게시글 조회 API")
    public List<BoardDto> selectBoardByKey(@PathVariable("key") Integer key) {
        boardService.updateLookup(key);
        return boardService.selectBoardByKey(key);
    }

    /**
     * 게시글 작성
     * @param boardInsertDto
     */
    @PostMapping("/PostBoard")
    @ApiOperation(value = "게시판 작성 API")
    public void insertBoard( @RequestBody BoardInsertDto boardInsertDto) {
        boardService.insertBoard(boardInsertDto);
    }

    /**
     * 게시글 수정
     * @param key key
     * @param boardUpdateDto
     */
    @PutMapping("/{key}")
    @ApiOperation(value = "게시글 수정 API")
    public void updateBoard(@PathVariable("key") Integer key, BoardUpdateDto boardUpdateDto) {
        boardUpdateDto.setBrdKey(key);
        boardService.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 삭제
     * @param key
     */
    @DeleteMapping("/{key}")
    @ApiOperation(value = "게시글 삭제 API")
    public void deleteBoard(@PathVariable("key") Integer key) {
        boardService.deleteBoard(key);
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/search")
    public List<BoardListDto> search(@RequestParam("keyword") String keyword) {
        return boardService.selectBoardByKeyword(keyword);
    }
}
