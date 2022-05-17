package com.graduate_ok.graduate_ok.service;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;

import java.util.List;

public interface NoticeService {
    // 공지사항 조회
    List<NoticeListDto> selectNoticeList();
    // 공지사항 검색
    List<NoticeListDto> selectNoticeByKeyword(String keyword);
}
