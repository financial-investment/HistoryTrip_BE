package com.ssafy.history.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.board.dto.NoticeDto;
import com.ssafy.history.board.mapper.NoticeMapper;
import com.ssafy.history.util.QuerySupport;

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
