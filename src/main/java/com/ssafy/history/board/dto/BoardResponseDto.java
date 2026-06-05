package com.ssafy.history.board.dto;

import java.time.LocalDateTime;

import com.ssafy.history.board.type.ContentType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Board post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private Long userId;
    private String userNickname;
    private Long tripPlanId;
    private String title;
    private String content;
    private ContentType contentType;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
