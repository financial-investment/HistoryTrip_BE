package com.ssafy.history.history.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.history.dto.HistoricalPlaceDto;

import com.ssafy.history.history.dto.PlaceMapDto;

import com.ssafy.history.history.dto.QuizRegionTagDto;
import com.ssafy.history.history.dto.PlaceImageDto;

import com.ssafy.history.history.dto.PlaceRegionDto;
import com.ssafy.history.history.service.PlaceService;

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


    @Operation(summary = "장소에 연결된 역사 태그 조회")
    @GetMapping("/{placeId}/tags")
    public List<QuizRegionTagDto> findTagsByPlaceId(@PathVariable long placeId) {
        return placeService.findTagsByPlaceId(placeId);
    }

    @Operation(summary = "장소 이미지 조회")
    @GetMapping("/{placeId}/images")
    public List<PlaceImageDto> findImagesByPlaceId(@PathVariable long placeId) {
        return placeService.findImagesByPlaceId(placeId);
    }
    
    @Operation(summary = "지도에 표시할 역사 관광지 마커 목록 조회")
    @GetMapping("/map")
    public ResponseEntity<List<PlaceMapDto>> findPlacesForMap(
            @Parameter(description = "시도명 필터") @RequestParam(required = false) String sidoName,
            @Parameter(description = "구군명 필터") @RequestParam(required = false) String gugunName,
            @Parameter(description = "관광지명, 주소, 지역명, 시대, 유형, 태그명 통합 검색어")
            @RequestParam(required = false) String keyword,
            @Parameter(description = "역사 태그명 필터") @RequestParam(required = false) String tagName,
            @Parameter(description = "지도 화면의 남쪽 위도") @RequestParam(required = false) BigDecimal minLat,
            @Parameter(description = "지도 화면의 북쪽 위도") @RequestParam(required = false) BigDecimal maxLat,
            @Parameter(description = "지도 화면의 서쪽 경도") @RequestParam(required = false) BigDecimal minLng,
            @Parameter(description = "지도 화면의 동쪽 경도") @RequestParam(required = false) BigDecimal maxLng,
            @RequestParam(defaultValue = "100") Integer limit) {
        return ResponseEntity.ok(placeService.findPlacesForMap(
                sidoName,
                gugunName,
                keyword,
                tagName,
                minLat,
                maxLat,
                minLng,
                maxLng,
                limit));
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
