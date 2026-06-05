package com.ssafy.history.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.history.board.dto.BoardCommentDto;
import com.ssafy.history.board.dto.BoardCreateDto;
import com.ssafy.history.board.dto.BoardResponseDto;
import com.ssafy.history.board.dto.BoardUpdateDto;
import com.ssafy.history.board.dto.BoardImageDto;
import com.ssafy.history.board.service.BoardService;
import com.ssafy.history.board.type.ContentType;

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
    public List<BoardResponseDto> findBoards(
            @Parameter(description = "제목/내용 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "게시글 타입") @RequestParam(required = false) ContentType contentType,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return boardService.findBoards(keyword, contentType, limit, offset);
    }

    @Operation(summary = "게시글 단건 조회")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> findBoardById(@PathVariable long boardId) {
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

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<Integer> createBoard(@RequestBody BoardCreateDto boardDto) {
    	int result = boardService.createBoard(boardDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{boardId}")
    public ResponseEntity<Integer> updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateDto boardDto) {
        boardDto.setBoardId(boardId);
        int result = boardService.updateBoard(boardDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Integer> deleteBoard(@PathVariable Long boardId) {
        int result = boardService.deleteBoard(boardId);
        return ResponseEntity.ok(result);
    }
    
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long commentId) {

        boardService.deleteComment(boardId, commentId);

        return ResponseEntity.ok().build();
    }
    
    @Operation(summary="댓글 작성")
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<Void> createComment(
            //@AuthenticationPrincipal UserPrincipal user,
            @PathVariable Long boardId,
            @RequestBody BoardCommentDto commentDto) {

    	commentDto.setBoardId(boardId);
        boardService.createComment(
            commentDto
        );

        return ResponseEntity.ok().build();
    }
    
    @Operation(summary="게시판에 이미지 등록")
    @PostMapping("/{boardId}/images")
    public ResponseEntity<Void> uploadImages(
            @PathVariable Long boardId,
            @RequestParam("image") List<MultipartFile> images) {

        boardService.uploadImages(boardId, images);
        return ResponseEntity.ok().build();
    }
    
}