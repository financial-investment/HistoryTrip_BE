package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.RegionHistoryTagDto;

public interface RegionHistoryTagMapper {
    List<RegionHistoryTagDto> findRegionHistoryTags(
            @Param("sidoName") String sidoName,
            @Param("gugunName") String gugunName,
            @Param("keyword") String keyword,
            @Param("tagType") String tagType,
            @Param("minWeight") Integer minWeight,
            @Param("needsReview") Boolean needsReview,
            @Param("limit") int limit,
            @Param("offset") int offset);

    List<RegionHistoryTagDto> findByRegionId(
            @Param("regionId") long regionId,
            @Param("limit") int limit,
            @Param("offset") int offset);
}
