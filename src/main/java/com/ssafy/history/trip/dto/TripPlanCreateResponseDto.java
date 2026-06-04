package com.ssafy.history.trip.dto;

import java.time.LocalDate;

import com.ssafy.history.trip.domain.TripPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TripPlanCreateResponseDto {
	private Long tripPlanId;
	private Long userId;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;

	public static TripPlanCreateResponseDto from(TripPlan tripPlan) {
		return new TripPlanCreateResponseDto(
				tripPlan.getId(),
				tripPlan.getUserId(),
				tripPlan.getTitle(),
				tripPlan.getDescription(),
				tripPlan.getStartDate(),
				tripPlan.getEndDate());
	}
}
