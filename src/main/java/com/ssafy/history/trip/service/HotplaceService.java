package com.ssafy.history.trip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.trip.dto.HotplaceDto;
import com.ssafy.history.trip.dto.HotplaceImageDto;
import com.ssafy.history.trip.mapper.HotplaceMapper;
import com.ssafy.history.util.QuerySupport;

@Service
public class HotplaceService {
    private final HotplaceMapper hotplaceMapper;

    public HotplaceService(HotplaceMapper hotplaceMapper) {
        this.hotplaceMapper = hotplaceMapper;
    }

    public List<HotplaceDto> findHotplaces(String keyword, String status, Integer limit, Integer offset) {
        return hotplaceMapper.findHotplaces(
                keyword,
                status,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public HotplaceDto findHotplaceById(long hotplaceId) {
        return hotplaceMapper.findHotplaceById(hotplaceId);
    }

    public List<HotplaceImageDto> findImagesByHotplaceId(long hotplaceId) {
        return hotplaceMapper.findImagesByHotplaceId(hotplaceId);
    }
}
