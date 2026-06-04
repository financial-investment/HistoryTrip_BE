package com.ssafy.history.region.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "History tag connected to a region")
@Getter
@Setter
public class RegionHistoryTagDto {
    private Long regionId;
    private String sidoName;
    private String gugunName;
    private Long tagId;
    private String tagName;
    private String tagType;
    private Integer weight;
    private String confidence;
    private Integer quizCount;
    private String evidenceSource;
    private String evidenceSummary;
    private String evidencePlaces;
    private String sourceUrl;
    private Boolean needsReview;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

   
}
