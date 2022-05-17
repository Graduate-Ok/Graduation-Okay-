package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class KyListDto {
    private String kyName1;
    private String kyType;
    private String kyCore;
    private Float kyCredit;
    private Integer kyCount;
}
