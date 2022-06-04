package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.*;
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
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 500, message = "server error"),
        @ApiResponse(code = 404, message = "not found")
})
public class BoardRestController {
    private final BoardService boardService;

    /**
     * 게시글 목록 조회
     */
    @GetMapping("/")
    @ApiOperation(value = "게시판 목록 조회 API")
    public BoardListDto selectBoardList(@RequestParam(value = "srchType", defaultValue = " ") String srchType,
                                        @RequestParam(value = "srchKeyword", defaultValue = " ") String srchKeyword,
                                        @RequestParam(value = "page", defaultValue = "1") String page) {
        return boardService.selectBoardList(srchType, srchKeyword, Integer.parseInt(page));
    }

    /**
     * 게시글 조회 + 조회수 증가
     */
    @GetMapping("/{key}")
    @ApiOperation(value = "게시글 조회 API")
    public List<BoardViewDto> selectBoardByKey(@PathVariable("key") Integer key) {
        return boardService.selectBoardByKey(key);
    }

    /**
     * 게시글 작성
     */
    @PostMapping("/PostBoard")
    @ApiOperation(value = "게시판 작성 API")
    public void insertBoard(@RequestBody BoardInsertDto boardInsertDto) {
        boardService.insertBoard(boardInsertDto);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{key}")
    @ApiOperation(value = "게시글 수정 API")
    public void updateBoard(@PathVariable("key") Integer key, BoardUpdateDto boardUpdateDto) {
        boardUpdateDto.setBrdKey(key);
        boardService.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{key}")
    @ApiOperation(value = "게시글 삭제 API")
    public String deleteBoard(@PathVariable("key") Integer key, BoardDeleteDto boardDeleteDto) {
        boardDeleteDto.setBrdKey(key);

        try {
            boardService.deleteBoard(boardDeleteDto);
            //
            return "성공적으로 삭제되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "비밀번호 오류";
        }
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/search")
    public List<BoardListDto> search(@RequestParam("keyword") String keyword) {
        return boardService.selectBoardByKeyword(keyword);
    }
}
