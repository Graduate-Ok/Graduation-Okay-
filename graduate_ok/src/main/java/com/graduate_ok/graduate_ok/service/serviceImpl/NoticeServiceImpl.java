package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.mapper.NoticeMapper;
import com.graduate_ok.graduate_ok.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeListDto> selectNoticeList() {
        return noticeMapper.selectNoticeList();
    }

    @Override
    public List<NoticeListDto> selectNoticeByKeyword(String keyword) {
        return noticeMapper.selectNoticeByKeyword(keyword);
    }
}
