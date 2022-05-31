package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.mapper.NoticeMapper;
import com.graduate_ok.graduate_ok.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeListDto> selectNoticeList(int data_index) {
        return noticeMapper.selectNoticeList(data_index);
    }

    @Override
    public List<NoticeListDto> selectNoticeByKeyword(String keyword) {
        return noticeMapper.selectNoticeByKeyword(keyword);
    }
}
