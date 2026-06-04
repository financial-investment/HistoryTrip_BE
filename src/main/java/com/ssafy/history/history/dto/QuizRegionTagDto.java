package com.ssafy.history.history.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "History concept tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRegionTagDto {
    private Long quizRegionTagsId;
    private String tagName;
    private Long quizId;
    private Long regionId;
}
