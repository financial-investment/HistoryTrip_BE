package com.ssafy.history.board.dto;

import java.time.LocalDateTime;

import com.ssafy.history.board.type.ContentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {
	private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private ContentType contentType;
}
