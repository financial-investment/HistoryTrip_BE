package com.ssafy.history.trip.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ssafy.history.trip.domain.TripPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TripPlanSummaryResponseDto {
    private Long tripPlanId;
    private Long userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int placeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TripPlanSummaryResponseDto from(TripPlan tripPlan, int placeCount) {
        return new TripPlanSummaryResponseDto(
                tripPlan.getId(),
                tripPlan.getUserId(),
                tripPlan.getTitle(),
                tripPlan.getDescription(),
                tripPlan.getStartDate(),
                tripPlan.getEndDate(),
                placeCount,
                tripPlan.getCreatedAt(),
                tripPlan.getUpdatedAt());
    }
}
