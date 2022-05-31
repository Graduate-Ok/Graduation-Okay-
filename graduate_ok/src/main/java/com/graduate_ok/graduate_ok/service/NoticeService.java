package com.graduate_ok.graduate_ok.service;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.dto.SearchHelper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
    // 공지사항 조회
    List<NoticeListDto> selectNoticeList(String srchType, String srchKeyword);
}
