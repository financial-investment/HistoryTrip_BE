package com.ssafy.history.news.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.news.dto.NewsDto;
import com.ssafy.history.news.dto.NewsResponseDto;
import com.ssafy.history.news.service.NewsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

	private final NewsService newsService;

	@Operation(summary = "역사 관광지 관련 뉴스 조회")
	@GetMapping("/{placeId}")
	public ResponseEntity<List<NewsResponseDto>> findNewsByPlaceId(@PathVariable long placeId) {
		List<NewsDto> newsList = newsService.findNewsByPlaceId(placeId);
		
		// 화면에는 제목과 URL만 필요하므로 응답 DTO로 축소한다.
		List<NewsResponseDto> list = new ArrayList<>();
		for(int i = 0;i<5;i++) {
			if(newsList.size() <= i) break;
			list.add(new NewsResponseDto(newsList.get(i).getTitle(), newsList.get(i).getUrl()));
		}
		
	    return ResponseEntity.ok(list);
	}
}
