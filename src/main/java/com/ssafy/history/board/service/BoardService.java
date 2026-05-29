package com.ssafy.history.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.board.dto.BoardCommentDto;
import com.ssafy.history.board.dto.BoardDto;
import com.ssafy.history.board.dto.BoardImageDto;
import com.ssafy.history.board.mapper.BoardMapper;
import com.ssafy.history.util.*;

@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDto> findBoards(String keyword, String boardType, Integer limit, Integer offset) {
        return boardMapper.findBoards(
                keyword,
                boardType,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public BoardDto findBoardById(long boardId) {
        return boardMapper.findBoardById(boardId);
    }

    public List<BoardCommentDto> findCommentsByBoardId(long boardId) {
        return boardMapper.findCommentsByBoardId(boardId);
    }

    public List<BoardImageDto> findImagesByBoardId(long boardId) {
        return boardMapper.findImagesByBoardId(boardId);
    }
}
