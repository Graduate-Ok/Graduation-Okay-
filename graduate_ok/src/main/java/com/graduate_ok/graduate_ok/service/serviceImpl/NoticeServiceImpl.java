package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.dto.SearchHelper;
import com.graduate_ok.graduate_ok.mapper.NoticeMapper;
import com.graduate_ok.graduate_ok.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeListDto> selectNoticeList(String srchType, String srchKeyword) {
        SearchHelper searchHelper = new SearchHelper();
        searchHelper.setSrchType(srchType);
        searchHelper.setSrchKeyword(srchKeyword);

        int totalCount = noticeMapper.countNoticeList(searchHelper);

        searchHelper = new SearchHelper(totalCount, searchHelper.getPage());

        searchHelper.setSrchType(srchType);
        searchHelper.setSrchKeyword(srchKeyword);

        List<NoticeListDto> list = noticeMapper.selectNoticeList(searchHelper);

        return list;
    }
}
