package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.*;
import com.graduate_ok.graduate_ok.mapper.BoardMapper;
import com.graduate_ok.graduate_ok.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     */
    @Override
    public BoardListDto selectBoardList(String srchType, String srchKeyword, int page) {
        BoardListDto boardListDto = new BoardListDto();

        SearchHelper searchHelper = new SearchHelper();
        searchHelper.setSrchType(srchType);
        searchHelper.setSrchKeyword(srchKeyword);

        int totalCount = boardMapper.countBoardList(searchHelper);

        searchHelper = new SearchHelper(totalCount, page, srchType, srchKeyword);

        List<BoardDto> list = boardMapper.selectBoardList(searchHelper);

        boardListDto.setBoardDtoList(list);
        boardListDto.setSearchHelper(searchHelper);

        return boardListDto;
    }

    /**
     * 게시글 조회 + 조회수 증가
     */
    @Override
    public List<BoardViewDto> selectBoardByKey(Integer key) {
        boardMapper.updateLookup(key);
        return boardMapper.selectBoardByKey(key);
    }

    /**
     * 게시글 작성
     */
    @Override
    public void insertBoard(BoardInsertDto boardInsertDto) {
        boardMapper.insertBoard(boardInsertDto);
    }

    /**
     * 수정할 게시글 조회
     */
    @Override
    public BoardUpdateDto selectEditBoardByKey(Integer key) {
        return boardMapper.selectEditBoardByKey(key);
    }

    /**
     * 게시글 수정
     */
    @Override
    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        boardMapper.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 삭제
     */
    @Override
    public void deleteBoard(BoardDeleteDto boardDeleteDto) {
        boardMapper.deleteBoard(boardDeleteDto);
    }
}
