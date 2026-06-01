package com.ssafy.history.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.board.dto.BoardCommentDto;
import com.ssafy.history.board.dto.BoardDto;
import com.ssafy.history.board.dto.BoardImageDto;

public interface BoardMapper {
    List<BoardDto> findBoards(
            @Param("keyword") String keyword,
            @Param("boardType") String boardType,
            @Param("limit") int limit,
            @Param("offset") int offset);

    BoardDto findBoardById(@Param("boardId") long boardId);

    List<BoardCommentDto> findCommentsByBoardId(@Param("boardId") long boardId);

    List<BoardImageDto> findImagesByBoardId(@Param("boardId") long boardId);
}
