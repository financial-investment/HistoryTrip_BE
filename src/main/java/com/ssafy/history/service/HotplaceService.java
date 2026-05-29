package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.HotplaceDto;
import com.ssafy.history.dto.HotplaceImageDto;
import com.ssafy.history.mapper.HotplaceMapper;

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
