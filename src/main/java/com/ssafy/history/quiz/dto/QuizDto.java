package com.ssafy.history.quiz.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Korean History Proficiency Test quiz image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long quizId;
    private String questionImageUrl;
    private String answer;
    private String source;
    private LocalDateTime createdAt;

}