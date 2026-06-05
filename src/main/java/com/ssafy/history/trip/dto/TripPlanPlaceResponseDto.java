package com.ssafy.history.trip.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripPlanPlaceResponseDto {
    private Long tripPlanPlaceId;
    private Long placeId;
    private String title;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String imageUrl;
    private Integer visitOrder;
    private LocalDate visitDate;
    private String memo;
}
