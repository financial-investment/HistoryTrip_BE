package com.ssafy.history.news.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.history.news.dto.NewsDto;

@Mapper
public interface NewsMapper {
    List<NewsDto> findNewsByPlaceId(@Param("placeId") long placeId);

    int countRecentNewsByPlaceId(
            @Param("placeId") long placeId,
            @Param("after") LocalDateTime after);

    int upsertNews(NewsDto news);

    Long findNewsIdByUrlHash(@Param("urlHash") String urlHash);

    int insertPlaceNewsIgnore(
            @Param("placeId") long placeId,
            @Param("newsId") long newsId);
}
