package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private String brdWriter;
    private String brdTitle;
    private String brdContent;
    private Timestamp brdWtTime;
    private Integer brdLookup;
}
