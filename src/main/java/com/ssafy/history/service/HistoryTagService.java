package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.mapper.HistoryTagMapper;

@Service
public class HistoryTagService {
    private final HistoryTagMapper historyTagMapper;

    public HistoryTagService(HistoryTagMapper historyTagMapper) {
        this.historyTagMapper = historyTagMapper;
    }

    public List<HistoryTagDto> findHistoryTags(String keyword, String tagType, Integer limit, Integer offset) {
        return historyTagMapper.findHistoryTags(
                keyword,
                tagType,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public HistoryTagDto findHistoryTagById(long tagId) {
        return historyTagMapper.findHistoryTagById(tagId);
    }
}
