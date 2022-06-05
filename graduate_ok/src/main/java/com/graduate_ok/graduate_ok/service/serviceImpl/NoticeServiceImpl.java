package com.graduate_ok.graduate_ok.service.serviceImpl;

import com.graduate_ok.graduate_ok.dto.BoardDto;
import com.graduate_ok.graduate_ok.dto.NoticeDto;
import com.graduate_ok.graduate_ok.dto.NoticeListDto;
import com.graduate_ok.graduate_ok.dto.SearchHelper;
import com.graduate_ok.graduate_ok.mapper.NoticeMapper;
import com.graduate_ok.graduate_ok.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
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

        // 타임스탬프 시간 변경 (9시간 추가)
        for (NoticeDto nd : list) {
            Timestamp original = nd.getNotiWtTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(original.getTime());
            cal.add(Calendar.HOUR, 9);
            Timestamp change = new Timestamp(cal.getTime().getTime());
            nd.setNotiWtTime(change);
        }

        noticeListDto.setNoticeDtoList(list);
        noticeListDto.setSearchHelper(searchHelper);

        return noticeListDto;
    }
}
