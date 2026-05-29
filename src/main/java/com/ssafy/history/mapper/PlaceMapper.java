package com.ssafy.history.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.dto.HistoricalPlaceDto;
import com.ssafy.history.dto.HistoryTagDto;
import com.ssafy.history.dto.NewsDto;
import com.ssafy.history.dto.PlaceImageDto;

public interface PlaceMapper {
    List<HistoricalPlaceDto> findPlaces(
            @Param("keyword") String keyword,
            @Param("sidoName") String sidoName,
            @Param("gugunName") String gugunName,
            @Param("limit") int limit,
            @Param("offset") int offset);

    HistoricalPlaceDto findPlaceById(@Param("placeId") long placeId);

    List<HistoryTagDto> findTagsByPlaceId(@Param("placeId") long placeId);

    List<PlaceImageDto> findImagesByPlaceId(@Param("placeId") long placeId);

    List<NewsDto> findNewsByPlaceId(@Param("placeId") long placeId);
}
