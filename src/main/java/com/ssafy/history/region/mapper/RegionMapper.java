package com.ssafy.history.region.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.region.dto.RegionDto;

public interface RegionMapper {
    List<RegionDto> findRegions(
            @Param("keyword") String keyword,
            @Param("limit") int limit,
            @Param("offset") int offset);

    RegionDto findRegionById(@Param("regionId") long regionId);
}
