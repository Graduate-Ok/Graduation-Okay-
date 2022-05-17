package com.graduate_ok.graduate_ok.service;


import com.graduate_ok.graduate_ok.dto.KyListDto;

import java.util.List;

public interface PopularKyService {
    List<KyListDto> selectKyList();
}
