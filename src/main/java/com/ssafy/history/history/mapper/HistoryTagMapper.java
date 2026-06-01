package com.ssafy.history.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.history.dto.HistoryTagDto;

public interface HistoryTagMapper {
    List<HistoryTagDto> findHistoryTags(
            @Param("keyword") String keyword,
            @Param("tagType") String tagType,
            @Param("limit") int limit,
            @Param("offset") int offset);

    HistoryTagDto findHistoryTagById(@Param("tagId") long tagId);
}
