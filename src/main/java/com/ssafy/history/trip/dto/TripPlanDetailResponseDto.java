package com.ssafy.history.trip.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.history.trip.domain.TripPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TripPlanDetailResponseDto {
    private Long tripPlanId;
    private Long userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TripPlanPlaceResponseDto> places;

    public static TripPlanDetailResponseDto from(TripPlan tripPlan, List<TripPlanPlaceResponseDto> places) {
        return new TripPlanDetailResponseDto(
                tripPlan.getId(),
                tripPlan.getUserId(),
                tripPlan.getTitle(),
                tripPlan.getDescription(),
                tripPlan.getStartDate(),
                tripPlan.getEndDate(),
                tripPlan.getCreatedAt(),
                tripPlan.getUpdatedAt(),
                places);
    }
}
