package com.ssafy.history.trip.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TripPlanPlaceUpdateDto {
    private LocalDate visitDate;
    private String memo;
}
