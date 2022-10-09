package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 * 정보공유 게시판 목록 DTO
 */
public class BoardListDto {
    List<BoardDto> boardDtoList;
    SearchHelper searchHelper;
}
