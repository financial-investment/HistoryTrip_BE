package com.ssafy.history.quiz.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.history.dto.QuizRegionTagDto;
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
			
			@RequestParam(defaultValue = "20") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
		return quizService.findQuizzes(limit, offset);
	}

	@Operation(summary = "한능검 퀴즈 단건 조회")
	@GetMapping("/{quizId}")
	public ResponseEntity<QuizDto> findQuizById(@PathVariable long quizId) {
		QuizDto quiz = quizService.findQuizById(quizId);

		if (quiz == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.of(Optional.ofNullable(quiz));
	}

	@Operation(summary = "퀴즈에 연결된 역사 태그 조회")
	@GetMapping("/{quizId}/tags")
	public List<QuizRegionTagDto> findTagsByQuizId(@PathVariable long quizId) {
		return quizService.findTagsByQuizId(quizId);
	}

	@Operation(summary = "지역 코드로 지역과 관련된 문제 조회")
	@GetMapping("/search")
	public List<QuizDto> findTagsByQuizId(
			@Parameter(description = "시도 코드") @RequestParam(required = false) String sidoCode,
			@Parameter(description = "구군 코드") @RequestParam(required = false) String gugunCode
			) {
		return quizService.findByRegionCode(sidoCode, gugunCode);
	}
	
	@Operation(summary = "문제 정답 맞추기")
	@PostMapping("/{quizId}/answer")
	public ResponseEntity<Map<String, Boolean>> submitAnswer(
			@PathVariable String quizId,
			@RequestParam String answer
			){
		
		
		if(quizId == null || answer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("correct", false));
		}
		
		boolean correct = quizService.submitAnswer(quizId, answer);
		return ResponseEntity.ok(Map.of("correct", correct));
	}
	
	
}
