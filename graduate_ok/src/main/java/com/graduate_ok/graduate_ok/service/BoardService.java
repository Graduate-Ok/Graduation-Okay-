package com.graduate_ok.graduate_ok.service;

import com.graduate_ok.graduate_ok.dto.*;

import java.util.List;

public interface BoardService {

    // 게시글 목록 조회
    BoardListDto selectBoardList(String srchType, String srchKeyword, int page);

    // 게시글 조회 + 조회수 증가
    List<BoardViewDto> selectBoardByKey(Integer key);

    // 게시글 작성
    void insertBoard(BoardInsertDto boardInsertDto);

    // 수정할 게시글 조회
    BoardUpdateDto selectEditBoardByKey(Integer key);

    // 게시글 수정
    void updateBoard(BoardUpdateDto boardUpdateDto);

    // 게시글 삭제
    void deleteBoard(BoardDeleteDto boardDeleteDto);
}
