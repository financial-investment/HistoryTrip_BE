package com.ssafy.history.news.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.history.history.dto.HistoricalPlaceDto;
import com.ssafy.history.news.dto.NewsDto;

@Component
public class GoogleNewsRssClient {

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    @Value("${news.google.rss.max-items:5}")
    private int maxItems;

    @Value("${news.google.rss.timeout-ms:10000}")
    private int timeoutMs;

    public List<NewsDto> searchByPlace(HistoricalPlaceDto place) throws Exception {
        // 장소명과 지역명을 조합해 Google News RSS 검색 URL을 만든다.
        String rssUrl = buildRssUrl(buildQuery(place));

        var doc = Jsoup.connect(rssUrl)
                .userAgent("Mozilla/5.0")
                .timeout(timeoutMs)
                .followRedirects(true)
                .parser(Parser.xmlParser())
                .get();

        List<NewsDto> result = new ArrayList<>();
        var items = doc.select("item");
        int limit = Math.min(maxItems, items.size());

        for (int i = 0; i < limit; i++) {
            Element item = items.get(i);

            // RSS item에서 API/DB에 필요한 최소 뉴스 정보만 추출한다.
            String title = text(item, "title");
            String url = text(item, "link");

            if (title.isBlank() || url.isBlank()) {
                continue;
            }

            NewsDto news = new NewsDto();
            news.setTitle(title);
            news.setUrl(url);
            news.setUrlHash(sha256(url));
            news.setSummary(text(item, "description"));
            news.setSource(text(item, "source"));
            news.setPublishedAt(parsePubDate(text(item, "pubDate")));

            result.add(news);
        }

        return result;
    }

    private String buildQuery(HistoricalPlaceDto place) {
        // 정확도를 높이기 위해 장소명은 따옴표로 묶고 지역명을 함께 검색한다.
        StringBuilder query = new StringBuilder();
        query.append("\"").append(place.getTitle()).append("\"");

        if (place.getSidoName() != null && !place.getSidoName().isBlank()) {
            query.append(" ").append(place.getSidoName());
        }

        if (place.getGugunName() != null && !place.getGugunName().isBlank()) {
            query.append(" ").append(place.getGugunName());
        }

//        query.append(" 관광 문화유산 역사 여행");
        query.append(" 관광 OR 문화유산 OR 역사 OR 여행");
        return query.toString();
    }

    private String buildRssUrl(String query) {
        return "https://news.google.com/rss/search?q="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&hl=ko&gl=KR&ceid=KR:ko";
    }

    private String text(Element parent, String selector) {
        Element element = parent.selectFirst(selector);
        return element == null ? "" : element.text();
    }

    private LocalDateTime parsePubDate(String pubDate) {
        try {
            // Google RSS의 RFC 1123 날짜를 KST 기준 LocalDateTime으로 변환한다.
            return OffsetDateTime.parse(pubDate, DateTimeFormatter.RFC_1123_DATE_TIME)
                    .atZoneSameInstant(KST)
                    .toLocalDateTime();
        } catch (Exception e) {
            return null;
        }
    }

    private String sha256(String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return HexFormat.of().formatHex(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
    }
}
