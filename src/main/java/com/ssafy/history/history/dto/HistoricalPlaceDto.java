package com.ssafy.history.history.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Historical place row")
@Getter
@Setter
public class HistoricalPlaceDto {
    private Long placeId;
    private Long regionId;
    private String sidoName;
    private String gugunName;
    private String title;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String description;
    private String heritageType;
    private String period;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private String imageUrl;
    
}
