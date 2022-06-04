package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.NoticeDto;
import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.dto.SearchHelper;
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
    public NoticeListDto selectNoticeList(String srchType, String srchKeyword, int page) {
        NoticeListDto noticeListDto = new NoticeListDto();

        SearchHelper searchHelper = new SearchHelper();
        searchHelper.setSrchType(srchType);
        searchHelper.setSrchKeyword(srchKeyword);

        int totalCount = noticeMapper.countNoticeList(searchHelper);

        searchHelper = new SearchHelper(totalCount, page, srchType, srchKeyword);

        List<NoticeDto> list = noticeMapper.selectNoticeList(searchHelper);

        noticeListDto.setNoticeDtoList(list);
        noticeListDto.setSearchHelper(searchHelper);

        return noticeListDto;
    }
}
