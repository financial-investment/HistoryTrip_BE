package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.NoticeDto;
import com.ssafy.history.mapper.NoticeMapper;

@Service
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<NoticeDto> findNotices(String keyword, Boolean pinned, Integer limit, Integer offset) {
        return noticeMapper.findNotices(
                keyword,
                pinned,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public NoticeDto findNoticeById(long noticeId) {
        return noticeMapper.findNoticeById(noticeId);
    }
}
