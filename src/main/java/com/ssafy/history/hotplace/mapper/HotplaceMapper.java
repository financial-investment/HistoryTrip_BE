package com.ssafy.history.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.hotplace.dto.HotplaceDto;
import com.ssafy.history.hotplace.dto.HotplaceImageDto;

public interface HotplaceMapper {
    List<HotplaceDto> findHotplaces(
            @Param("keyword") String keyword,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset);

    HotplaceDto findHotplaceById(@Param("hotplaceId") long hotplaceId);

    List<HotplaceImageDto> findImagesByHotplaceId(@Param("hotplaceId") long hotplaceId);
}
