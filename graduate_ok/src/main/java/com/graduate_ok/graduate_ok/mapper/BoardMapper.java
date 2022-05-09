package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.BoardInsertDto;
import com.graduate_ok.graduate_ok.dto.BoardListDto;
import com.graduate_ok.graduate_ok.dto.BoardUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

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

}
