package com.graduate_ok.graduate_ok.service;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.BoardInsertDto;
import com.graduate_ok.graduate_ok.dto.BoardListDto;
import com.graduate_ok.graduate_ok.dto.BoardUpdateDto;

import java.util.List;

public interface BoardService {

    // 게시글 목록 조회
    List<BoardListDto> selectBoardList();

    // 게시글 조회 + 조회수 증가
    List<BoardDto> selectBoardByKey(Integer key);
    void updateLookup(Integer key);

    // 게시글 작성
    void insertBoard(BoardInsertDto boardInsertDto);

    // 게시글 수정
    void updateBoard(BoardUpdateDto boardUpdateDto);

    // 게시글 삭제
    void deleteBoard(Integer key);

    // 게시글 검색
    List<BoardListDto> selectBoardByKeyword(String keyword);
}
