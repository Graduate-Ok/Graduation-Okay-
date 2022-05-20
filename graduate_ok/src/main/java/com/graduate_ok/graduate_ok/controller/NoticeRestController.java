package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.service.NoticeService;
import com.graduate_ok.graduate_ok.service.serviceImpl.NoticeServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Notice")
@RequiredArgsConstructor
public class NoticeRestController{
    private final NoticeServiceImpl noticeService;
    /*
    *  공지사항 조회
    * */
    @GetMapping("/list")
    public List<NoticeListDto> selectNoticeList() {
        return noticeService.selectNoticeList();
    }

    /*
     * 공지사항 검색
     * */
    @GetMapping("/search")
    public List<NoticeListDto> search(@RequestParam("keyword") String keyword) {
        return noticeService.selectNoticeByKeyword(keyword);
    }

}
