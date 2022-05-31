package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.dto.SearchHelper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 공지사항 게시판 조회
    List<NoticeListDto> selectNoticeList(SearchHelper searchHelper);

    // 공지사항 게시물 카운트
    int countNoticeList(SearchHelper searchHelper);
}
