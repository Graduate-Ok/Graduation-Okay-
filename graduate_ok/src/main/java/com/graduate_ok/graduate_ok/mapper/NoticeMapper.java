package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 공지사항 조회
    List<NoticeListDto> selectNoticeList();

    // 공지사항 검색
    List<NoticeListDto> selectNoticeByKeyword(String keyword);
}
