package com.ssafy.history.board.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Board image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardImageDto {
    private Long imageId;
    private Long boardId;
    private String imageUrl;
    private String originalName;
    private String savedName;
    private LocalDateTime createdAt;
}
