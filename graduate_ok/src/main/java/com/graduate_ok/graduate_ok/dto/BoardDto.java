package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Integer brdKey;
    private String brdTitle;
    private String brdWriter;
    private String brdWtTime;
    private Integer brdLookup;
}
