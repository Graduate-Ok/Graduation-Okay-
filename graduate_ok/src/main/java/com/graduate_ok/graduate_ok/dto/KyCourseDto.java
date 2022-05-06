package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KyCourseDto {
    private Integer kyKey;
    private String kyName1;
    private String kyName2;
    private Float kyCredit;
    private Integer kyRequired;
    private String kyType;
    private String kyCore;
    private Integer kyCount;
    private Integer delFlag;
}
