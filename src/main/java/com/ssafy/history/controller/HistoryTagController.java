package com.ssafy.history.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.service.HistoryTagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "History Tags", description = "시대, 사건, 인물, 장소 유형, 한능검 키워드 조회")
@RestController
@RequestMapping("/api/history-tags")
public class HistoryTagController {
    private final HistoryTagService historyTagService;

    public HistoryTagController(HistoryTagService historyTagService) {
        this.historyTagService = historyTagService;
    }

    @Operation(summary = "역사 태그 목록 조회")
    @GetMapping
    public List<HistoryTagDto> findHistoryTags(
            @Parameter(description = "태그명 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "PERIOD, EVENT, PERSON, PLACE_TYPE, KEYWORD, PLACE")
            @RequestParam(required = false) String tagType,
            @RequestParam(defaultValue = "50") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return historyTagService.findHistoryTags(keyword, tagType, limit, offset);
    }

    @Operation(summary = "역사 태그 단건 조회")
    @GetMapping("/{tagId}")
    public ResponseEntity<HistoryTagDto> findHistoryTagById(@PathVariable long tagId) {
        return ResponseEntity.of(Optional.ofNullable(historyTagService.findHistoryTagById(tagId)));
    }
}
