package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.NoticeDto;

public interface NoticeMapper {
    List<NoticeDto> findNotices(
            @Param("keyword") String keyword,
            @Param("pinned") Boolean pinned,
            @Param("limit") int limit,
            @Param("offset") int offset);

    NoticeDto findNoticeById(@Param("noticeId") long noticeId);
}
