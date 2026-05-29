package com.ssafy.history.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.dto.HistoricalPlaceDto;
import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.dto.NewsDto;
import com.ssafy.history.dto.PlaceImageDto;
import com.ssafy.history.service.PlaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Historical Places", description = "역사 관광지와 연결 데이터 조회")
@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Operation(summary = "역사 관광지 목록 조회")
    @GetMapping
    public List<HistoricalPlaceDto> findPlaces(
            @Parameter(description = "장소명/주소/설명/시대 검색어") @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sidoName,
            @RequestParam(required = false) String gugunName,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return placeService.findPlaces(keyword, sidoName, gugunName, limit, offset);
    }

    @Operation(summary = "역사 관광지 단건 조회")
    @GetMapping("/{placeId}")
    public ResponseEntity<HistoricalPlaceDto> findPlaceById(@PathVariable long placeId) {
        return ResponseEntity.of(Optional.ofNullable(placeService.findPlaceById(placeId)));
    }

    @Operation(summary = "장소에 연결된 역사 태그 조회")
    @GetMapping("/{placeId}/tags")
    public List<HistoryTagDto> findTagsByPlaceId(@PathVariable long placeId) {
        return placeService.findTagsByPlaceId(placeId);
    }

    @Operation(summary = "장소 이미지 조회")
    @GetMapping("/{placeId}/images")
    public List<PlaceImageDto> findImagesByPlaceId(@PathVariable long placeId) {
        return placeService.findImagesByPlaceId(placeId);
    }

    @Operation(summary = "장소 관련 뉴스 조회")
    @GetMapping("/{placeId}/news")
    public List<NewsDto> findNewsByPlaceId(@PathVariable long placeId) {
        return placeService.findNewsByPlaceId(placeId);
    }
}
