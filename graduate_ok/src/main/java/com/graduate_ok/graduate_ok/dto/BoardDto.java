package com.graduate_ok.graduate_ok.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * 정보공유 게시판 DTO
 */
public class BoardDto {
    @ApiParam(value = "게시글 번호")
    private Integer brdKey;

    @ApiParam(value = "게시글 제목")
    private String brdTitle;

    @ApiParam(value = "게시글 작성자")
    private String brdWriter;

    @ApiParam(value = "게시글 작성시간")
    private String brdWtTime;

    @ApiParam(value = "게시글 조회수")
    private Integer brdLookup;
}
