package com.ssafy.history.region.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.region.dto.RegionDto;
import com.ssafy.history.region.mapper.RegionMapper;
import com.ssafy.history.util.QuerySupport;

@Service
public class RegionService {
    private final RegionMapper regionMapper;

    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    public List<RegionDto> findRegions(String keyword, Integer limit, Integer offset) {
        return regionMapper.findRegions(keyword, QuerySupport.normalizeLimit(limit), QuerySupport.normalizeOffset(offset));
    }

    public RegionDto findRegionById(long regionId) {
        return regionMapper.findRegionById(regionId);
    }
}
