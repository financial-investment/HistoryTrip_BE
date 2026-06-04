package com.ssafy.history.quiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.history.dto.QuizRegionTagDto;
import com.ssafy.history.quiz.dto.QuizDto;

public interface QuizMapper {
    List<QuizDto> findQuizzes(
            @Param("limit") int limit,
            @Param("offset") int offset);

    QuizDto findQuizById(@Param("quizId") long quizId);

    List<QuizRegionTagDto> findTagsByQuizId(@Param("quizId") long quizId);
    
    List<QuizDto> findRandomQuizByRegion(@Param("sidoCode") String sidoCode, @Param("gugunCode") String gugunCode);

	void insertQuizResult(@Param("quizId")String quizId,@Param("userId")Long userId, @Param("userAnswer")String userAnswer, @Param("isCorrect")boolean correct);
    
}
