package com.ssafy.history.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.history.dto.HistoryTagDto;
import com.ssafy.history.history.dto.PlaceImageDto;
import com.ssafy.history.history.dto.PlaceRegionDto;
import com.ssafy.history.news.dto.NewsDto;

public interface PlaceMapper {
    List<HistoricalPlaceDto> searchPlaces(
            @Param("keyword") String keyword,
            @Param("sidoName") String sidoName,
            @Param("gugunName") String gugunName,
            @Param("tagName") String tagName,
            @Param("limit") int limit,
            @Param("offset") int offset);

    PlaceRegionDto findRegionByPlaceId(@Param("placeId") long placeId);
    
    HistoricalPlaceDto findPlaceById(@Param("placeId") long placeId);

    List<HistoryTagDto> findTagsByPlaceId(@Param("placeId") long placeId);

    List<PlaceImageDto> findImagesByPlaceId(@Param("placeId") long placeId);

    List<NewsDto> findNewsByPlaceId(@Param("placeId") long placeId);

    
}
