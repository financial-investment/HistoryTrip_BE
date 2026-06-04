package com.ssafy.history.trip.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TripPlanPlace {
    private Long id;
    private Long placeId;
    private int visitOrder;
    private LocalDate visitDate;
    private String memo;

    // TripPlan 안에 새로 추가되는 장소 방문 정보를 만든다.
    public TripPlanPlace(Long placeId, int visitOrder, LocalDate visitDate, String memo) {
        if (placeId == null) {
            throw new IllegalArgumentException("placeId is required.");
        }
        changeOrder(visitOrder);
        this.placeId = placeId;
        this.visitDate = visitDate;
        this.memo = memo;
    }

    // visitOrder는 화면에 표시되는 루트 순서이므로 1부터 시작한다.
    public void changeOrder(int visitOrder) {
        if (visitOrder < 1) {
            throw new IllegalArgumentException("visitOrder must be greater than 0.");
        }
        this.visitOrder = visitOrder;
    }

    // 장소 자체가 아니라 이 여행 계획에서의 방문 정보만 바꾼다.
    public void updateVisitInfo(LocalDate visitDate, String memo) {
        this.visitDate = visitDate;
        this.memo = memo;
    }
}
