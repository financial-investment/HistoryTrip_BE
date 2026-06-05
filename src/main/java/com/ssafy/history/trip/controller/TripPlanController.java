package com.ssafy.history.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.trip.dto.TripPlanCreateResponseDto;
import com.ssafy.history.trip.dto.TripPlanDetailResponseDto;
import com.ssafy.history.trip.dto.TripPlanDto;
import com.ssafy.history.trip.dto.TripPlanPlaceDto;
import com.ssafy.history.trip.dto.TripPlanPlaceUpdateDto;
import com.ssafy.history.trip.dto.TripPlanPlacesOrderDto;
import com.ssafy.history.trip.dto.TripPlanSummaryResponseDto;
import com.ssafy.history.trip.service.TripPlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Trip Plans", description = "여행 계획 생성, 조회, 수정, 삭제 및 여행 계획 내 관광지 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trip-plans")
public class TripPlanController {
    private final TripPlanService service;

    @Operation(summary = "여행 계획 생성", description = "여행 계획의 제목, 설명, 시작일, 종료일을 저장하고 생성된 여행 계획 ID를 반환합니다.")
    @PostMapping
    public ResponseEntity<TripPlanCreateResponseDto> createTripPlan(
            @Parameter(description = "현재는 임시로 요청 파라미터로 전달하는 사용자 ID")
            @RequestParam Long userId,
            @RequestBody TripPlanDto tripPlanDto) {
        return ResponseEntity.ok(service.createTripPlan(userId, tripPlanDto));
    }

    @Operation(summary = "내 여행 계획 목록 조회", description = "사용자가 생성한 여행 계획 목록을 최신 수정 순으로 조회합니다.")
    @GetMapping("/my")
    public ResponseEntity<List<TripPlanSummaryResponseDto>> findMyTripPlans(
            @Parameter(description = "조회할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "조회 개수")
            @RequestParam(defaultValue = "20") Integer limit,
            @Parameter(description = "조회 시작 위치")
            @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.ok(service.findMyTripPlans(userId, limit, offset));
    }

    @Operation(summary = "여행 계획 상세 조회", description = "여행 계획 기본 정보와 계획에 포함된 역사 관광지 목록을 함께 조회합니다.")
    @GetMapping("/{tripPlanId}")
    public ResponseEntity<TripPlanDetailResponseDto> findTripPlan(
            @Parameter(description = "조회할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId) {
        return ResponseEntity.ok(service.findTripPlan(userId, tripPlanId));
    }

    @Operation(summary = "여행 계획 기본 정보 수정", description = "여행 계획의 제목, 설명, 시작일, 종료일을 수정합니다.")
    @PutMapping("/{tripPlanId}")
    public ResponseEntity<TripPlanDetailResponseDto> updateTripPlan(
            @Parameter(description = "수정할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId,
            @RequestBody TripPlanDto tripPlanDto) {
        return ResponseEntity.ok(service.updateTripPlan(userId, tripPlanId, tripPlanDto));
    }

    @Operation(summary = "여행 계획 삭제", description = "여행 계획과 그 안에 포함된 관광지 목록을 삭제합니다.")
    @DeleteMapping("/{tripPlanId}")
    public ResponseEntity<Void> deleteTripPlan(
            @Parameter(description = "삭제할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId) {
        service.deleteTripPlan(userId, tripPlanId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "여행 계획에 관광지 추가", description = "지도에서 선택한 역사 관광지를 여행 계획에 추가합니다.")
    @PostMapping("/{tripPlanId}/places")
    public ResponseEntity<TripPlanDetailResponseDto> addPlace(
            @Parameter(description = "수정할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId,
            @RequestBody TripPlanPlaceDto placeDto) {
        return ResponseEntity.ok(service.addPlace(userId, tripPlanId, placeDto));
    }

    @Operation(summary = "여행 계획 내 관광지 순서 변경", description = "여행 계획에 포함된 관광지들의 방문 순서를 일괄 변경합니다.")
    @PutMapping("/{tripPlanId}/places/order")
    public ResponseEntity<TripPlanDetailResponseDto> reorderPlaces(
            @Parameter(description = "수정할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId,
            @RequestBody TripPlanPlacesOrderDto orderDto) {
        return ResponseEntity.ok(service.reorderPlaces(userId, tripPlanId, orderDto));
    }

    @Operation(summary = "여행 계획 내 관광지 방문 정보 수정", description = "여행 계획에 추가된 특정 관광지의 방문 날짜와 메모를 수정합니다.")
    @PutMapping("/{tripPlanId}/places/{tripPlanPlaceId}")
    public ResponseEntity<TripPlanDetailResponseDto> updatePlace(
            @Parameter(description = "수정할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId,
            @Parameter(description = "여행 계획에 추가된 관광지 항목 ID")
            @PathVariable long tripPlanPlaceId,
            @RequestBody TripPlanPlaceUpdateDto placeUpdateDto) {
        return ResponseEntity.ok(service.updatePlace(userId, tripPlanId, tripPlanPlaceId, placeUpdateDto));
    }

    @Operation(summary = "여행 계획에서 관광지 삭제", description = "여행 계획에 추가된 특정 관광지를 제거하고 남은 관광지 순서를 다시 정리합니다.")
    @DeleteMapping("/{tripPlanId}/places/{tripPlanPlaceId}")
    public ResponseEntity<TripPlanDetailResponseDto> removePlace(
            @Parameter(description = "수정할 사용자 ID")
            @RequestParam Long userId,
            @Parameter(description = "여행 계획 ID")
            @PathVariable long tripPlanId,
            @Parameter(description = "여행 계획에 추가된 관광지 항목 ID")
            @PathVariable long tripPlanPlaceId) {
        return ResponseEntity.ok(service.removePlace(userId, tripPlanId, tripPlanPlaceId));
    }
}
