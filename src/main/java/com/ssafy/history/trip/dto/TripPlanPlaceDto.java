package com.ssafy.history.trip.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TripPlanPlaceDto {
    private Long placeId;
    private LocalDate visitDate;
    private String memo;
}
