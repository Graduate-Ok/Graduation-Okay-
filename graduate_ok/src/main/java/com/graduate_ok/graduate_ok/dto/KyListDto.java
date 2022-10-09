package com.graduate_ok.graduate_ok.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * 교양 목록 DTO
 */
public class KyListDto {
    @ApiParam(value = "교양 이름")
    private String kyName1;

    @ApiParam(value = "교양 인재상")
    private String kyType;

    @ApiParam(value = "교양 핵심역량")
    private String kyCore;

    @ApiParam(value = "교양 학점")
    private Float kyCredit;

    @ApiParam(value = "교양 수강횟수")
    private Integer kyCount;
}
