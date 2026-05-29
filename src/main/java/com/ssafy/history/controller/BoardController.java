package com.ssafy.history.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.dto.BoardCommentDto;
import com.ssafy.history.dto.BoardDto;
import com.ssafy.history.dto.BoardImageDto;
import com.ssafy.history.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Boards", description = "역사 여행 후기 및 코스 공유 게시판 조회")
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping
    public List<BoardDto> findBoards(
            @Parameter(description = "제목/내용 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "FREE, TRIP_REVIEW, COURSE_SHARE") @RequestParam(required = false) String boardType,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return boardService.findBoards(keyword, boardType, limit, offset);
    }

    @Operation(summary = "게시글 단건 조회")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> findBoardById(@PathVariable long boardId) {
        return ResponseEntity.of(Optional.ofNullable(boardService.findBoardById(boardId)));
    }

    @Operation(summary = "게시글 댓글 조회")
    @GetMapping("/{boardId}/comments")
    public List<BoardCommentDto> findCommentsByBoardId(@PathVariable long boardId) {
        return boardService.findCommentsByBoardId(boardId);
    }

    @Operation(summary = "게시글 이미지 조회")
    @GetMapping("/{boardId}/images")
    public List<BoardImageDto> findImagesByBoardId(@PathVariable long boardId) {
        return boardService.findImagesByBoardId(boardId);
    }
}
