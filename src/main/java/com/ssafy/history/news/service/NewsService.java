package com.ssafy.history.news.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.history.mapper.PlaceMapper;
import com.ssafy.history.news.dto.NewsDto;
import com.ssafy.history.news.mapper.NewsMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
	private final PlaceMapper placeMapper;
    private final NewsMapper newsMapper;
    private final GoogleNewsRssClient googleNewsRssClient;

    @Value("${news.google.rss.refresh-hours:6}")
    private int refreshHours;

    @Transactional
    public List<NewsDto> findNewsByPlaceId(long placeId) {
        HistoricalPlaceDto place = placeMapper.findPlaceById(placeId);
        if (place == null) {
            return List.of();
        }

        // 최근 수집 데이터가 없으면 요청 시점에 RSS를 호출해 캐시한다.
        if (shouldCrawl(placeId)) {
            crawlAndSave(place);
        }

        return newsMapper.findNewsByPlaceId(placeId);
    }

    private boolean shouldCrawl(long placeId) {
        if (refreshHours <= 0) {
            return true;
        }

        // refreshHours 안에 저장된 뉴스가 있으면 외부 호출을 생략한다.
        LocalDateTime after = LocalDateTime.now().minusHours(refreshHours);
        return newsMapper.countRecentNewsByPlaceId(placeId, after) == 0;
    }

    private void crawlAndSave(HistoricalPlaceDto place) {
        try {
            List<NewsDto> newsList = googleNewsRssClient.searchByPlace(place);

            for (NewsDto news : newsList) {

                newsMapper.upsertNews(news);

                Long newsId = newsMapper.findNewsIdByUrlHash(news.getUrlHash());
                if (newsId == null) {
                    continue;
                }
    
                newsMapper.insertPlaceNewsIgnore(place.getPlaceId(), newsId);
            }
        } catch (Exception e) {
            log.warn("Google News RSS crawl failed. placeId={}, title={}",
                    place.getPlaceId(), place.getTitle(), e);
        }
    }
}
