package com.graduate_ok.graduate_ok.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDeleteDto {
    @ApiParam(value = "게시글 번호")
    private Integer brdKey;
    @ApiParam(value = "게시글 비밀번호")
    private String brdPassword;

    public BoardDeleteDto(Integer brdKey, String brdPassword) {
        this.brdKey = brdKey;
        this.brdPassword = brdPassword;
    }
}
