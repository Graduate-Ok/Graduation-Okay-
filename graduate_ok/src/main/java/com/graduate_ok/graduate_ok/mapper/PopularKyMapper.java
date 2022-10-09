package com.graduate_ok.graduate_ok.mapper;

import com.graduate_ok.graduate_ok.dto.KyListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopularKyMapper {
    // 인기교양 조회
    List<KyListDto> selectKyList();

}
