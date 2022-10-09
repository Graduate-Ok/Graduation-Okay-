package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.service.serviceImpl.NoticeServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Notice")
@ApiResponses({
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 500, message = "server error"),
        @ApiResponse(code = 404, message = "not found")
})
public class NoticeRestController {
    private final NoticeServiceImpl noticeService;

    /**
     * 공지사항 목록 조회 + 검색 + 페이징 + notice 탭 추가
     */
    @GetMapping("/")
    @ApiOperation(value = "공지사항 게시판 조회 API")
    public NoticeListDto selectNoticeList(@RequestParam(value = "notiCategory", defaultValue = " ") String notiCategory,
                                          @RequestParam(value = "srchType", defaultValue = " ") String srchType,
                                          @RequestParam(value = "srchKeyword", defaultValue = " ") String srchKeyword,
                                          @RequestParam(value = "page", defaultValue = "1") String page) {
        return noticeService.selectNoticeList(notiCategory, srchType, srchKeyword, Integer.parseInt(page));
    }

}
