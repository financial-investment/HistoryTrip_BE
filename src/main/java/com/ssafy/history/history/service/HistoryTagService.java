package com.ssafy.history.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.history.dto.QuizRegionTagDto;
import com.ssafy.history.history.mapper.HistoryTagMapper;
import com.ssafy.history.util.QuerySupport;

@Service
public class HistoryTagService {
    private final HistoryTagMapper historyTagMapper;

    public HistoryTagService(HistoryTagMapper historyTagMapper) {
        this.historyTagMapper = historyTagMapper;
    }

    public List<QuizRegionTagDto> findHistoryTags(String keyword, String tagType, Integer limit, Integer offset) {
        return historyTagMapper.findHistoryTags(
                keyword,
                tagType,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public QuizRegionTagDto findHistoryTagById(long tagId) {
        return historyTagMapper.findHistoryTagById(tagId);
    }
}
