package com.graduate_ok.graduate_ok.controller;

import com.graduate_ok.graduate_ok.dto.KyListDto;
import com.graduate_ok.graduate_ok.service.serviceImpl.PopularKyServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/KyRecommend")
@ApiResponses({
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 500, message = "server error"),
        @ApiResponse(code = 404, message = "not found")
})
public class PopularKyRestController {
    private final PopularKyServiceImpl popularKyService;

    @GetMapping("/")
    @ApiOperation(value = "정보공유 게시판 조회 API")
    public List<KyListDto> selectKyList(){
        return popularKyService.selectKyList();
    }
}
