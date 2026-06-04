package com.ssafy.history.history.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.history.dto.HistoryTagDto;
import com.ssafy.history.history.dto.PlaceImageDto;
import com.ssafy.history.history.dto.PlaceMapDto;
import com.ssafy.history.history.dto.PlaceRegionDto;
import com.ssafy.history.history.mapper.PlaceMapper;
import com.ssafy.history.news.dto.NewsDto;
import com.ssafy.history.util.QuerySupport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceMapper placeMapper;

    public List<HistoricalPlaceDto> searchPlaces(
            String keyword,
            String sidoName,
            String gugunName,
            String tagName,
            Integer limit,
            Integer offset) {
        return placeMapper.searchPlaces(
                keyword,
                sidoName,
                gugunName,
                tagName,
                QuerySupport.normalizeLimit(limit),
                QuerySupport.normalizeOffset(offset));
    }

    public List<PlaceMapDto> findPlacesForMap(
            String sidoName,
            String gugunName,
            String keyword,
            String tagName,
            BigDecimal minLat,
            BigDecimal maxLat,
            BigDecimal minLng,
            BigDecimal maxLng,
            Integer limit) {
        return placeMapper.findPlacesForMap(
                sidoName,
                gugunName,
                keyword,
                tagName,
                minLat,
                maxLat,
                minLng,
                maxLng,
                QuerySupport.normalizeLimit(limit));
    }
    
    public PlaceRegionDto findRegionByPlaceId(long placeId) {
        return placeMapper.findRegionByPlaceId(placeId);
    }
    
    

    public HistoricalPlaceDto findPlaceById(long placeId) {
        return placeMapper.findPlaceById(placeId);
    }

    public List<HistoryTagDto> findTagsByPlaceId(long placeId) {
        return placeMapper.findTagsByPlaceId(placeId);
    }

    public List<PlaceImageDto> findImagesByPlaceId(long placeId) {
        return placeMapper.findImagesByPlaceId(placeId);
    }

    public List<NewsDto> findNewsByPlaceId(long placeId) {
        return placeMapper.findNewsByPlaceId(placeId);
    }

    
}
