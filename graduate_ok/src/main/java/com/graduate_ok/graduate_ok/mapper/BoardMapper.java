package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 목록 조회
    List<BoardDto> selectBoardList(SearchHelper searchHelper);

    // 게시글 카운트
    int countBoardList(SearchHelper searchHelper);

    // 게시글 조회 + 조회수 증가
    List<BoardViewDto> selectBoardByKey(Integer key);
    void updateLookup(Integer key);

    // 게시글 작성
    void insertBoard(BoardInsertDto boardInsertDto);

    // 수정할 게시글 조회
    BoardUpdateDto selectEditBoardByKey(Integer key);

    // 게시글 수정
    void updateBoard(BoardUpdateDto boardUpdateDto);

    // 게시글 삭제
    void deleteBoard(BoardDeleteDto boardDeleteDto);

    // 게시글 검색
    List<BoardListDto> selectBoardByKeyword(String keyword);

}
