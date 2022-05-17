package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.KyListDto;
import com.graduate_ok.graduate_ok.mapper.PopularKyMapper;
import com.graduate_ok.graduate_ok.service.PopularKyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularKyServiceImpl implements PopularKyService {

    private final PopularKyMapper popularKyMapper;

    @Override
    public List<KyListDto> selectKyList() {
        return popularKyMapper.selectKyList();
    }
}
