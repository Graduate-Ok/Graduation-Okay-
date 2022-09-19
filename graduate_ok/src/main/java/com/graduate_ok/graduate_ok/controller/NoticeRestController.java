package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.service.serviceImpl.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Notice")
@RequiredArgsConstructor
public class NoticeRestController {
    private final NoticeServiceImpl noticeService;

    /**
     * 공지사항 목록 조회 + 검색 + 페이징 + notice 탭 추가
     */
    @GetMapping("/")
    public NoticeListDto selectNoticeList(@RequestParam(value = "notiCategory", defaultValue = " ") String notiCategory,
                                          @RequestParam(value = "srchType", defaultValue = " ") String srchType,
                                          @RequestParam(value = "srchKeyword", defaultValue = " ") String srchKeyword,
                                          @RequestParam(value = "page", defaultValue = "1") String page) {
        return noticeService.selectNoticeList(notiCategory, srchType, srchKeyword, Integer.parseInt(page));
    }

}
