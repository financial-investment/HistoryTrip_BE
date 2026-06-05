package com.ssafy.history.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.board.dto.BoardCommentDto;
import com.ssafy.history.board.dto.BoardCreateDto;
import com.ssafy.history.board.dto.BoardResponseDto;
import com.ssafy.history.board.dto.BoardUpdateDto;
import com.ssafy.history.board.dto.BoardImageDto;
import com.ssafy.history.board.type.ContentType;

public interface BoardMapper {
	List<BoardResponseDto> findBoards(@Param("keyword") String keyword, @Param("contentType") ContentType contentType,
			@Param("limit") int limit, @Param("offset") int offset);

	BoardResponseDto findBoardById(@Param("boardId") long boardId);

	List<BoardCommentDto> findCommentsByBoardId(@Param("boardId") long boardId);

	List<BoardImageDto> findImagesByBoardId(@Param("boardId") long boardId);

	int createBoard(BoardCreateDto boardDto);
	
	int createCommentByBoardId(BoardCommentDto commentDto);

	int updateBoard(BoardUpdateDto boardDto);

	int deleteBoard(Long boardId);

	int deleteCommentsByBoardId(Long boardId, Long commentId);

	int deleteAllCommentsByBoardId(Long boardId);

	int deleteImagesByBoardId(Long boardId, Long imageId);

	int deleteAllImagesByBoardId(Long boardId);

	void createBoardImage(BoardImageDto imageDto);



}
