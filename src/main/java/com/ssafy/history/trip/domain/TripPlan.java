package com.ssafy.history.trip.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ssafy.history.trip.dto.TripPlanDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripPlan {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TripPlanPlace> places = new ArrayList<>();

    public TripPlan(long userId, TripPlanDto tripPlanDto) {
        this.userId = userId;
        updateInfo(
                tripPlanDto.getTitle(),
                tripPlanDto.getDescription(),
                tripPlanDto.getStartDate(),
                tripPlanDto.getEndDate());
    }

    // MyBatis로 따로 조회한 장소 목록을 TripPlan 객체 안에 조립한다.
    public void loadPlaces(List<TripPlanPlace> places) {
        this.places = places == null ? new ArrayList<>() : new ArrayList<>(places);
    }

    // 계획에 장소를 추가할 때 중복과 방문 날짜 범위를 TripPlan이 직접 검증한다.
    public TripPlanPlace addPlace(Long placeId, LocalDate visitDate, String memo) {
        validateVisitDate(visitDate);
        if (containsPlace(placeId)) {
            throw new IllegalArgumentException("This place already exists in the trip plan.");
        }

        TripPlanPlace place = new TripPlanPlace(placeId, nextVisitOrder(), visitDate, memo);
        places.add(place);
        return place;
    }

    // 장소 삭제 후 남은 장소의 방문 순서를 연속된 값으로 다시 맞춘다.
    public void removePlace(Long tripPlanPlaceId) {
        TripPlanPlace target = findPlace(tripPlanPlaceId);
        places.remove(target);
        reorderSequentially();
    }

    // 클라이언트가 보낸 전체 순서가 현재 계획의 장소들과 정확히 일치하는지 검증한다.
    public void reorderPlaces(List<Long> orderedTripPlanPlaceIds) {
        if (orderedTripPlanPlaceIds == null || orderedTripPlanPlaceIds.size() != places.size()) {
            throw new IllegalArgumentException("All trip plan places must be included for reordering.");
        }

        Set<Long> requestedIds = new HashSet<>(orderedTripPlanPlaceIds);
        if (requestedIds.size() != orderedTripPlanPlaceIds.size()) {
            throw new IllegalArgumentException("Duplicate trip plan place ids are not allowed.");
        }

        for (TripPlanPlace place : places) {
            if (!requestedIds.contains(place.getId())) {
                throw new IllegalArgumentException("Only places in this trip plan can be reordered.");
            }
        }

        for (int i = 0; i < orderedTripPlanPlaceIds.size(); i++) {
            findPlace(orderedTripPlanPlaceIds.get(i)).changeOrder(i + 1);
        }
    }

    // 계획 안에 추가된 장소의 방문 날짜와 메모만 수정한다.
    public void updatePlaceMemo(Long tripPlanPlaceId, LocalDate visitDate, String memo) {
        validateVisitDate(visitDate);
        findPlace(tripPlanPlaceId).updateVisitInfo(visitDate, memo);
    }

    // 계획의 기본 정보 변경 규칙을 TripPlan 내부에서 관리한다.
    public void updateInfo(String title, String description, LocalDate startDate, LocalDate endDate) {
        validateTitle(title);
        validatePeriod(startDate, endDate);
        validatePlacesInPeriod(startDate, endDate);

        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // 같은 여행 계획에 같은 역사 관광지를 두 번 추가하지 않기 위한 확인이다.
    private boolean containsPlace(Long placeId) {
        return places.stream().anyMatch(place -> place.getPlaceId().equals(placeId));
    }

    // 계획에 포함된 장소만 수정/삭제/정렬할 수 있도록 찾기 로직을 한곳에 둔다.
    private TripPlanPlace findPlace(Long tripPlanPlaceId) {
        return places.stream()
                .filter(place -> place.getId().equals(tripPlanPlaceId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Trip plan place not found."));
    }

    // 새 장소는 현재 마지막 순서 다음에 붙인다.
    private int nextVisitOrder() {
        return places.stream()
                .mapToInt(TripPlanPlace::getVisitOrder)
                .max()
                .orElse(0) + 1;
    }

    // 장소 삭제 후 1, 2, 3... 형태로 순서를 다시 만든다.
    private void reorderSequentially() {
        places.sort((left, right) -> Integer.compare(left.getVisitOrder(), right.getVisitOrder()));
        for (int i = 0; i < places.size(); i++) {
            places.get(i).changeOrder(i + 1);
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title is required.");
        }
    }

    private void validatePeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate cannot be after endDate.");
        }
    }

    private void validateVisitDate(LocalDate visitDate) {
        if (visitDate == null) {
            return;
        }
        if (startDate != null && visitDate.isBefore(startDate)) {
            throw new IllegalArgumentException("visitDate cannot be before startDate.");
        }
        if (endDate != null && visitDate.isAfter(endDate)) {
            throw new IllegalArgumentException("visitDate cannot be after endDate.");
        }
    }

    private void validatePlacesInPeriod(LocalDate startDate, LocalDate endDate) {
        for (TripPlanPlace place : places) {
            LocalDate visitDate = place.getVisitDate();
            if (visitDate == null) {
                continue;
            }
            if (startDate != null && visitDate.isBefore(startDate)) {
                throw new IllegalArgumentException("A place visitDate is before startDate.");
            }
            if (endDate != null && visitDate.isAfter(endDate)) {
                throw new IllegalArgumentException("A place visitDate is after endDate.");
            }
        }
    }
}
