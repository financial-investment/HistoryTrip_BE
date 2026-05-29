package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.RegionHistoryTagDto;
import com.ssafy.history.mapper.RegionHistoryTagMapper;

@Service
public class RegionHistoryTagService {
    private final RegionHistoryTagMapper regionHistoryTagMapper;

    public RegionHistoryTagService(RegionHistoryTagMapper regionHistoryTagMapper) {
        this.regionHistoryTagMapper = regionHistoryTagMapper;
    }

    public List<RegionHistoryTagDto> findRegionHistoryTags(
            String sidoName,
            String gugunName,
            String keyword,
            String tagType,
            Integer minWeight,
            Boolean needsReview,
            Integer limit,
            Integer offset) {
        return regionHistoryTagMapper.findRegionHistoryTags(
                sidoName,
                gugunName,
                keyword,
                tagType,
                minWeight,
                needsReview,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public List<RegionHistoryTagDto> findByRegionId(long regionId, Integer limit, Integer offset) {
        return regionHistoryTagMapper.findByRegionId(
                regionId,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }
}
