package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.RegionDto;

public interface RegionMapper {
    List<RegionDto> findRegions(
            @Param("keyword") String keyword,
            @Param("limit") int limit,
            @Param("offset") int offset);

    RegionDto findRegionById(@Param("regionId") long regionId);
}
