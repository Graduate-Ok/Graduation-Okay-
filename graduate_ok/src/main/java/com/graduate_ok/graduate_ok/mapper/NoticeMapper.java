package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 공지사항 조회
    List<NoticeListDto> selectNoticeList( int data_index);

    // 공지사항 검색
    List<NoticeListDto> selectNoticeByKeyword(String keyword);
}
