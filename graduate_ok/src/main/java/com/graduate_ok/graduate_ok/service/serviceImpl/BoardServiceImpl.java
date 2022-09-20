package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.*;
import com.graduate_ok.graduate_ok.mapper.BoardMapper;
import com.graduate_ok.graduate_ok.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
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

        // 타임스탬프 시간 변경 (9시간 추가) // 로컬에서 돌릴 때 해당
//        for (BoardDto bd : list) {
//            Timestamp original = bd.getBrdWtTime();
//            Calendar cal = Calendar.getInstance();
//            cal.setTimeInMillis(original.getTime());
//            cal.add(Calendar.HOUR, 9);
//            Timestamp change = new Timestamp(cal.getTime().getTime());
//            bd.setBrdWtTime(change);
//        }

        // Unix Time -> Timestamp 변환
        for (BoardDto bd : list) {
            Long original = Long.parseLong(bd.getBrdWtTime().toString());
            Date date = new Date(original*1000);
            Timestamp changed = new Timestamp(date.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(changed.getTime());
            cal.add(Calendar.HOUR, 9);
            bd.setBrdWtTime(new Timestamp(cal.getTime().getTime()));
        }

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

    /**
     * 게시글 검색
     */
    @Override
    public List<BoardListDto> selectBoardByKeyword(String keyword) {
        return boardMapper.selectBoardByKeyword(keyword);
    }
}
