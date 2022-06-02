package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.service.serviceImpl.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Notice")
@RequiredArgsConstructor
public class NoticeRestController {
    private final NoticeServiceImpl noticeService;

    /**
     * ?srchType=title&srchKeyword=title
     * 공지사항 목록 조회 + 검색 + 페이징
     */
    @GetMapping("/list")
    public NoticeListDto selectNoticeList(@RequestParam(value = "srchType", defaultValue = " ") String srchType,
                                          @RequestParam(value = "srchKeyword", defaultValue = " ") String srchKeyword,
                                          @RequestParam(value = "page", defaultValue = "1") String page) {
        return noticeService.selectNoticeList(srchType, srchKeyword, Integer.parseInt(page));
    }

}
