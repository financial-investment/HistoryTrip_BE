package com.ssafy.history.history.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Historical place marker for map")
@Getter
@Setter
public class PlaceMapDto {
    private Long placeId;
    private String title;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String heritageType;
    private String period;
    private String imageUrl;
    private String sidoName;
    private String gugunName;
}
