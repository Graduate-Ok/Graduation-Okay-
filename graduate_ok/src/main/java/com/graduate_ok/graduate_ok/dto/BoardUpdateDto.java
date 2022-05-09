package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateDto {
    private Integer brdKey;
    private String brdWriter;
    private String brdPassword;
    private String brdTitle;
    private String brdContent;
}