package com.ssafy.history.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.board.dto.NoticeDto;
import com.ssafy.history.board.service.NoticeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notices", description = "공지사항 조회")
@RestController
@RequestMapping("/api/notices")
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    public List<NoticeDto> findNotices(
            @Parameter(description = "제목/내용 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "상단 고정 여부") @RequestParam(required = false) Boolean pinned,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return noticeService.findNotices(keyword, pinned, limit, offset);
    }

    @Operation(summary = "공지사항 단건 조회")
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeDto> findNoticeById(@PathVariable long noticeId) {
        return ResponseEntity.of(Optional.ofNullable(noticeService.findNoticeById(noticeId)));
    }
}
