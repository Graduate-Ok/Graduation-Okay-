package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDto {
    private Integer notiKey;
    private String notiCategory;
    private String notiTitle;
    private String notiContent;
    private String notiWtTime;
}
