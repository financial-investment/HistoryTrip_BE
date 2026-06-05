package com.ssafy.history.trip.dto;

import java.util.List;

import lombok.Data;

@Data
public class TripPlanPlacesOrderDto {
    private List<Long> tripPlanPlaceIds;
}
