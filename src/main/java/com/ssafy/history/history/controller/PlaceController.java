package com.ssafy.history.history.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.history.dto.HistoryTagDto;
import com.ssafy.history.history.dto.PlaceImageDto;
import com.ssafy.history.history.dto.PlaceRegionDto;
import com.ssafy.history.history.service.PlaceService;
import com.ssafy.history.news.dto.NewsDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Historical Places", description = "Historical place search and lookup")
@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @Operation(summary = "통합 키워드 검색, 지역+태그로 필터링 가능")
    @GetMapping
    public ResponseEntity<List<HistoricalPlaceDto>> searchPlaces(
            @Parameter(description = "Search keyword for place, address, region, period, type, or tag")
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sidoName,
            @RequestParam(required = false) String gugunName,
            @RequestParam(required = false) String tagName,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(placeService.searchPlaces(keyword, sidoName, gugunName, tagName, limit, offset));
    }
    
    @Operation(summary = "특정 관광지의 지역 찾기")
    @GetMapping("/{placeId}/region")
    public ResponseEntity<PlaceRegionDto> findRegionByPlaceId(@PathVariable long placeId) {
        return ResponseEntity.of(Optional.ofNullable(placeService.findRegionByPlaceId(placeId)));
    }
    
    

//    @Operation(summary = "Find historical place by id")
//    @GetMapping("/{placeId}")
//    public ResponseEntity<HistoricalPlaceDto> findPlaceById(@PathVariable long placeId) {
//        return ResponseEntity.of(Optional.ofNullable(placeService.findPlaceById(placeId)));
//    }
//
//    @Operation(summary = "Find tags linked to a historical place")
//    @GetMapping("/{placeId}/tags")
//    public List<HistoryTagDto> findTagsByPlaceId(@PathVariable long placeId) {
//        return placeService.findTagsByPlaceId(placeId);
//    }
//
//    @Operation(summary = "Find images linked to a historical place")
//    @GetMapping("/{placeId}/images")
//    public List<PlaceImageDto> findImagesByPlaceId(@PathVariable long placeId) {
//        return placeService.findImagesByPlaceId(placeId);
//    }
//
//    @Operation(summary = "Find news linked to a historical place")
//    @GetMapping("/{placeId}/news")
//    public List<NewsDto> findNewsByPlaceId(@PathVariable long placeId) {
//        return placeService.findNewsByPlaceId(placeId);
//    }
}
