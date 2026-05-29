package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.dto.QuizDto;

public interface QuizMapper {
    List<QuizDto> findQuizzes(
            @Param("keyword") String keyword,
            @Param("difficulty") String difficulty,
            @Param("tagName") String tagName,
            @Param("tagType") String tagType,
            @Param("limit") int limit,
            @Param("offset") int offset);

    QuizDto findQuizById(@Param("quizId") long quizId);

    List<HistoryTagDto> findTagsByQuizId(@Param("quizId") long quizId);
}
