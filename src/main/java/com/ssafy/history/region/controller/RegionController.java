package com.ssafy.history.region.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.region.dto.RegionDto;
import com.ssafy.history.region.dto.RegionHistoryTagDto;
import com.ssafy.history.region.service.RegionHistoryTagService;
import com.ssafy.history.region.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Regions", description = "지역 및 지역별 역사 태그 조회")
@RestController
@RequestMapping("/api/regions")
public class RegionController {
    private final RegionService regionService;
    private final RegionHistoryTagService regionHistoryTagService;

    public RegionController(RegionService regionService, RegionHistoryTagService regionHistoryTagService) {
        this.regionService = regionService;
        this.regionHistoryTagService = regionHistoryTagService;
    }

    @Operation(summary = "지역 목록 조회")
    @GetMapping
    public List<RegionDto> findRegions(
            @Parameter(description = "시도/구군명 검색어") @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return regionService.findRegions(keyword, limit, offset);
    }

    @Operation(summary = "지역 단건 조회")
    @GetMapping("/{regionId}")
    public ResponseEntity<RegionDto> findRegionById(@PathVariable long regionId) {
        return ResponseEntity.of(Optional.ofNullable(regionService.findRegionById(regionId)));
    }

    @Operation(summary = "특정 지역의 역사 태그 조회")
    @GetMapping("/{regionId}/history-tags")
    public List<RegionHistoryTagDto> findHistoryTagsByRegionId(
            @PathVariable long regionId,
            @RequestParam(defaultValue = "50") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return regionHistoryTagService.findByRegionId(regionId, limit, offset);
    }
}
