package com.ssafy.history.history.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Region information for a historical place")
@Getter
@Setter
public class PlaceRegionDto {
    private Long placeId;
    private Long regionId;
    private String sidoName;
    private String gugunName;
}