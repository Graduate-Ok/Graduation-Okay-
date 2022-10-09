package com.graduate_ok.graduate_ok.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * 공지사항 게시판 DTO
 */
public class NoticeDto {
    @ApiParam(value = "게시글 번호")
    private Integer notiKey;

    @ApiParam(value = "게시글 분류")
    private String notiCategory;

    @ApiParam(value = "게시글 제목")
    private String notiTitle;

    @ApiParam(value = "게시글 내용")
    private String notiContent;

    @ApiParam(value = "게시글 작성시간")
    private String notiWtTime;
}
