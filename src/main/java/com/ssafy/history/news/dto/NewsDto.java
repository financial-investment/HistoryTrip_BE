package com.ssafy.history.news.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "News item related to a historical place")
@Getter
@Setter
public class NewsDto {
    private Long newsId;
    private String title;
    private String url;
    private String urlHash;
    private String summary;
    private String source;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
}
