package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.dto.QuizDto;
import com.ssafy.history.mapper.QuizMapper;

@Service
public class QuizService {
    private final QuizMapper quizMapper;

    public QuizService(QuizMapper quizMapper) {
        this.quizMapper = quizMapper;
    }

    public List<QuizDto> findQuizzes(
            String keyword,
            String difficulty,
            String tagName,
            String tagType,
            Integer limit,
            Integer offset) {
        return quizMapper.findQuizzes(
                keyword,
                difficulty,
                tagName,
                tagType,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public QuizDto findQuizById(long quizId) {
        return quizMapper.findQuizById(quizId);
    }

    public List<HistoryTagDto> findTagsByQuizId(long quizId) {
        return quizMapper.findTagsByQuizId(quizId);
    }
}
