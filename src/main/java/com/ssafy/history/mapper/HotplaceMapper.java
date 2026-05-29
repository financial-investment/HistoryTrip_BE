package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.HotplaceDto;
import com.ssafy.history.dto.HotplaceImageDto;

public interface HotplaceMapper {
    List<HotplaceDto> findHotplaces(
            @Param("keyword") String keyword,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset);

    HotplaceDto findHotplaceById(@Param("hotplaceId") long hotplaceId);

    List<HotplaceImageDto> findImagesByHotplaceId(@Param("hotplaceId") long hotplaceId);
}
