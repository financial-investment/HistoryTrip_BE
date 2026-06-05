package com.ssafy.history.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.history.board.dto.BoardCommentDto;
import com.ssafy.history.board.dto.BoardCreateDto;
import com.ssafy.history.board.dto.BoardResponseDto;
import com.ssafy.history.board.dto.BoardUpdateDto;
import com.ssafy.history.board.dto.BoardImageDto;
import com.ssafy.history.board.mapper.BoardMapper;
import com.ssafy.history.board.type.ContentType;
import com.ssafy.history.util.*;

@Service
public class BoardService {
    private final BoardMapper boardMapper;
    private final FileService fileService;

    public BoardService(
            BoardMapper boardMapper,
            FileService fileService) {

        this.boardMapper = boardMapper;
        this.fileService = fileService;
    }

    
    public List<BoardResponseDto> findBoards(String keyword, ContentType contentType, Integer limit, Integer offset) {
        return boardMapper.findBoards(
                keyword,
                contentType,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public BoardResponseDto findBoardById(long boardId) {
        return boardMapper.findBoardById(boardId);
    }

    public List<BoardCommentDto> findCommentsByBoardId(long boardId) {
        return boardMapper.findCommentsByBoardId(boardId);
    }

    public List<BoardImageDto> findImagesByBoardId(long boardId) {
        return boardMapper.findImagesByBoardId(boardId);
    }
    
    public int createBoard(BoardCreateDto boardDto) {
    	boardMapper.createBoard(boardDto);
    	return boardDto.getBoardId().intValue();
    }
    
    public int updateBoard(BoardUpdateDto boardDto) {
    	return boardMapper.updateBoard(boardDto);
    }
    
    
    //게시글 삭제 시 포함된 이미지, 댓글까지 모두 삭제 필요.
    public int deleteBoard(Long boardId) {
    	return boardMapper.deleteBoard(boardId);
    }

	public int createComment(BoardCommentDto commentDto) {
		return boardMapper.createCommentByBoardId(commentDto);
		
	}
	
	public void uploadImages(Long boardId, List<MultipartFile> images) {
	    for (MultipartFile image : images) {
	        String imageUrl = fileService.save(image);

	        BoardImageDto imageDto = new BoardImageDto();
	        imageDto.setBoardId(boardId);
	        imageDto.setImageUrl(imageUrl);
	        imageDto.setOriginalName(image.getOriginalFilename());

	        boardMapper.createBoardImage(imageDto);
	    }
	}
	
	
	public void deleteComment(Long boardId, Long commentId) {
	    int result = boardMapper.deleteCommentsByBoardId(boardId, commentId);

	    if (result == 0) {
	        throw new RuntimeException("댓글을 찾을 수 없습니다.");
	    }
	}
    
    
}
