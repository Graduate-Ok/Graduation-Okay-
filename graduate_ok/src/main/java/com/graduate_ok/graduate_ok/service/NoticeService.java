package com.graduate_ok.graduate_ok.service;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;

public interface NoticeService {
    // 공지사항 조회
    NoticeListDto selectNoticeList(String srchType, String srchKeyword, int page);
}
