package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.BoardInsertDto;
import com.graduate_ok.graduate_ok.dto.BoardListDto;
import com.graduate_ok.graduate_ok.dto.BoardUpdateDto;
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
     * @return
     */
    @Override
    public List<BoardListDto> selectBoardList() {
        return boardMapper.selectBoardList();
    }

    /**
     * 게시글 조회
     * @param key
     * @return
     */
    @Override
    public List<BoardDto> selectBoardByKey(Integer key) {
        return boardMapper.selectBoardByKey(key);
    }

    /**
     * 조회수 증가
     * @param key
     */
    @Override
    public void updateLookup(Integer key) {
        boardMapper.updateLookup(key);
    }

    /**
     * 게시글 작성
     * @param boardInsertDto
     */
    @Override
    public void insertBoard(BoardInsertDto boardInsertDto) {
        boardMapper.insertBoard(boardInsertDto);
    }

    /**
     * 게시글 수정
     * @param boardUpdateDto
     */
    @Override
    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        boardMapper.updateBoard(boardUpdateDto);
    }

    /**
     * 게시글 삭제
     * @param key
     */
    @Override
    public void deleteBoard(Integer key) {
        boardMapper.deleteBoard(key);
    }

    /**
     * 게시글 검색
     */
    @Override
    public List<BoardListDto> selectBoardByKeyword(String keyword) {
        return boardMapper.selectBoardByKeyword(keyword);
    }
}
