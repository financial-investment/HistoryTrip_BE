package com.ssafy.history.quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.history.dto.HistoryTagDto;
import com.ssafy.history.quiz.dto.QuizDto;
import com.ssafy.history.quiz.service.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Quizzes", description = "한능검 문제 이미지와 역사 태그 조회")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(summary = "한능검 퀴즈 목록 조회", description = "선지 테이블은 사용하지 않고, 선지까지 포함된 문제 이미지 자체를 반환합니다.")
    @GetMapping
    public List<QuizDto> findQuizzes(
            @Parameter(description = "문제명 또는 source 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "LOW, MEDIUM, HIGH") @RequestParam(required = false) String difficulty,
            @Parameter(description = "연결 태그명 검색어") @RequestParam(required = false) String tagName,
            @Parameter(description = "PERIOD, EVENT, PERSON, PLACE_TYPE, KEYWORD, PLACE")
            @RequestParam(required = false) String tagType,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return quizService.findQuizzes(keyword, difficulty, tagName, tagType, limit, offset);
    }

    @Operation(summary = "한능검 퀴즈 단건 조회")
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> findQuizById(@PathVariable long quizId) {
        return ResponseEntity.of(Optional.ofNullable(quizService.findQuizById(quizId)));
    }

    @Operation(summary = "퀴즈에 연결된 역사 태그 조회")
    @GetMapping("/{quizId}/tags")
    public List<HistoryTagDto> findTagsByQuizId(@PathVariable long quizId) {
        return quizService.findTagsByQuizId(quizId);
    }
}
