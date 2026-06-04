package com.ssafy.history.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.history.dto.QuizRegionTagDto;
import com.ssafy.history.history.dto.PlaceImageDto;
import com.ssafy.history.history.mapper.PlaceMapper;
import com.ssafy.history.news.dto.NewsDto;
import com.ssafy.history.util.QuerySupport;

@Service
public class PlaceService {
    private final PlaceMapper placeMapper;

    public PlaceService(PlaceMapper placeMapper) {
        this.placeMapper = placeMapper;
    }

    public List<HistoricalPlaceDto> findPlaces(
            String keyword,
            String sidoName,
            String gugunName,
            Integer limit,
            Integer offset) {
        return placeMapper.findPlaces(
                keyword,
                sidoName,
                gugunName,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public HistoricalPlaceDto findPlaceById(long placeId) {
        return placeMapper.findPlaceById(placeId);
    }

    public List<QuizRegionTagDto> findTagsByPlaceId(long placeId) {
        return placeMapper.findTagsByPlaceId(placeId);
    }

    public List<PlaceImageDto> findImagesByPlaceId(long placeId) {
        return placeMapper.findImagesByPlaceId(placeId);
    }

    public List<NewsDto> findNewsByPlaceId(long placeId) {
        return placeMapper.findNewsByPlaceId(placeId);
    }
}
