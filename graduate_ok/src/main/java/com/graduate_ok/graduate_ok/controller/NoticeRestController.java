package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.service.serviceImpl.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notice")
@RequiredArgsConstructor
public class NoticeRestController{
    private final NoticeServiceImpl noticeService;

    /**
     * 공지사항 목록 조회 + 검색 + 페이징
     */
    @GetMapping("/list/{srchType}/{srchKeyword}")
    public List<NoticeListDto> selectNoticeList(
            @PathVariable("srchType") String srchType,
            @PathVariable("srchKeyword") String srchKeyword
    ) {
        return noticeService.selectNoticeList(srchType, srchKeyword);
    }

}
