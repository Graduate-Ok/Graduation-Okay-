package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoardListDto {
    private Integer brdKey;
    private String brdWriter;
    private String brdTitle;
    private Timestamp brdWtTime;
    private Integer brdLookup;
}
