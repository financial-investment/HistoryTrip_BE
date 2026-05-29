package com.ssafy.history.region.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.region.dto.RegionHistoryTagDto;
import com.ssafy.history.region.service.RegionHistoryTagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Region History Tags", description = "지역과 역사/한능검 태그 연결 조회")
@RestController
@RequestMapping("/api/region-history-tags")
public class RegionHistoryTagController {
    private final RegionHistoryTagService regionHistoryTagService;

    public RegionHistoryTagController(RegionHistoryTagService regionHistoryTagService) {
        this.regionHistoryTagService = regionHistoryTagService;
    }

    @Operation(summary = "지역-역사 태그 목록 조회")
    @GetMapping
    public List<RegionHistoryTagDto> findRegionHistoryTags(
            @RequestParam(required = false) String sidoName,
            @RequestParam(required = false) String gugunName,
            @Parameter(description = "태그명/근거 요약/근거 장소 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "PERIOD, EVENT, PERSON, PLACE_TYPE, KEYWORD, PLACE")
            @RequestParam(required = false) String tagType,
            @RequestParam(required = false) Integer minWeight,
            @RequestParam(required = false) Boolean needsReview,
            @RequestParam(defaultValue = "50") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return regionHistoryTagService.findRegionHistoryTags(
                sidoName,
                gugunName,
                keyword,
                tagType,
                minWeight,
                needsReview,
                limit,
                offset);
    }
}
