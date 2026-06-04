package com.ssafy.history.trip.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TripPlanDto {
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
}
