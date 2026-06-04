package com.ssafy.history.trip.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ssafy.history.trip.domain.TripPlan;
import com.ssafy.history.trip.domain.TripPlanPlace;
import com.ssafy.history.trip.dto.TripPlanCreateResponseDto;
import com.ssafy.history.trip.dto.TripPlanDetailResponseDto;
import com.ssafy.history.trip.dto.TripPlanDto;
import com.ssafy.history.trip.dto.TripPlanPlaceDto;
import com.ssafy.history.trip.dto.TripPlanPlaceResponseDto;
import com.ssafy.history.trip.dto.TripPlanPlaceUpdateDto;
import com.ssafy.history.trip.dto.TripPlanPlacesOrderDto;
import com.ssafy.history.trip.dto.TripPlanSummaryResponseDto;
import com.ssafy.history.trip.mapper.TripPlanMapper;
import com.ssafy.history.util.QuerySupport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripPlanService {
    private final TripPlanMapper tripPlanMapper;

    // 새 여행 계획을 만들고, DB가 생성한 tripPlanId를 응답으로 돌려준다.
    @Transactional
    public TripPlanCreateResponseDto createTripPlan(Long userId, TripPlanDto tripPlanDto) {
        if (tripPlanDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tripPlan body is required.");
        }
        TripPlan tripPlan = new TripPlan(requiredUserId(userId), tripPlanDto);
        tripPlanMapper.insertTripPlan(tripPlan);
        return TripPlanCreateResponseDto.from(tripPlan);
    }

    // 마이페이지/목록 화면용: 계획 기본 정보와 포함 장소 개수만 조회한다.
    public List<TripPlanSummaryResponseDto> findMyTripPlans(Long userId, Integer limit, Integer offset) {
        long ownerId = requiredUserId(userId);
        return tripPlanMapper.findTripPlansByUserId(
                        ownerId,
                        QuerySupport.normalizeLimit(limit),
                        QuerySupport.normalizeOffset(offset))
                .stream()
                .map(tripPlan -> TripPlanSummaryResponseDto.from(
                        tripPlan,
                        tripPlanMapper.countTripPlanPlacesByTripPlanId(tripPlan.getId())))
                .toList();
    }

    // 상세 화면용: 계획 기본 정보와 지도/목록에 보여줄 장소 정보를 함께 조회한다.
    public TripPlanDetailResponseDto findTripPlan(Long userId, long tripPlanId) {
        TripPlan tripPlan = findOwnedTripPlan(userId, tripPlanId);
        List<TripPlanPlaceResponseDto> places = tripPlanMapper.findTripPlanPlaceResponsesByTripPlanId(tripPlanId);
        return TripPlanDetailResponseDto.from(tripPlan, places);
    }

    // 계획 기간 변경은 이미 추가된 장소들의 방문 날짜가 새 기간 안에 있는지 도메인 객체가 검증한다.
    @Transactional
    public TripPlanDetailResponseDto updateTripPlan(Long userId, long tripPlanId, TripPlanDto tripPlanDto) {
        if (tripPlanDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tripPlan body is required.");
        }
        TripPlan tripPlan = findOwnedTripPlanWithPlaces(userId, tripPlanId);
        tripPlan.updateInfo(
                tripPlanDto.getTitle(),
                tripPlanDto.getDescription(),
                tripPlanDto.getStartDate(),
                tripPlanDto.getEndDate());
        tripPlanMapper.updateTripPlan(tripPlan);
        return findTripPlan(userId, tripPlanId);
    }

    // trip_plan_places는 DB FK cascade로 함께 삭제된다.
    @Transactional
    public void deleteTripPlan(Long userId, long tripPlanId) {
        int deleted = tripPlanMapper.deleteTripPlanByIdAndUserId(tripPlanId, requiredUserId(userId));
        if (deleted == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip plan not found.");
        }
    }

    // 장소 추가 순서와 중복 여부는 TripPlan 객체가 판단한다.
    @Transactional
    public TripPlanDetailResponseDto addPlace(Long userId, long tripPlanId, TripPlanPlaceDto placeDto) {
        if (placeDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "place body is required.");
        }
        TripPlan tripPlan = findOwnedTripPlanWithPlaces(userId, tripPlanId);
        TripPlanPlace place = tripPlan.addPlace(
                placeDto.getPlaceId(),
                placeDto.getVisitDate(),
                placeDto.getMemo());
        tripPlanMapper.insertTripPlanPlace(tripPlanId, place);
        return findTripPlan(userId, tripPlanId);
    }

    // 계획 안의 장소 한 개에 대한 방문 날짜/메모만 변경한다.
    @Transactional
    public TripPlanDetailResponseDto updatePlace(
            Long userId,
            long tripPlanId,
            long tripPlanPlaceId,
            TripPlanPlaceUpdateDto placeUpdateDto) {
        if (placeUpdateDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "place body is required.");
        }
        TripPlan tripPlan = findOwnedTripPlanWithPlaces(userId, tripPlanId);
        tripPlan.updatePlaceMemo(
                tripPlanPlaceId,
                placeUpdateDto.getVisitDate(),
                placeUpdateDto.getMemo());

        TripPlanPlace place = tripPlan.getPlaces().stream()
                .filter(candidate -> candidate.getId().equals(tripPlanPlaceId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip plan place not found."));
        tripPlanMapper.updateTripPlanPlaceVisitInfo(tripPlanId, place);
        return findTripPlan(userId, tripPlanId);
    }

    // unique(trip_plan_id, visit_order) 제약을 피하려고 임시 음수 순서로 밀어둔 뒤 최종 순서를 저장한다.
    @Transactional
    public TripPlanDetailResponseDto reorderPlaces(Long userId, long tripPlanId, TripPlanPlacesOrderDto orderDto) {
        if (orderDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "order body is required.");
        }
        TripPlan tripPlan = findOwnedTripPlanWithPlaces(userId, tripPlanId);
        tripPlan.reorderPlaces(orderDto.getTripPlanPlaceIds());

        //현재 DB index의 제약 : 같은 순서를 가진 관광지는 unique 해야 한다. 때문에 temporaryOrder로 초기화 한 이후 다시 넣는 것
        int temporaryOrder = -1000;
        for (TripPlanPlace place : tripPlan.getPlaces()) {
            tripPlanMapper.updateTripPlanPlaceOrder(tripPlanId, place.getId(), temporaryOrder--);
        }
        for (TripPlanPlace place : tripPlan.getPlaces()) {
            tripPlanMapper.updateTripPlanPlaceOrder(tripPlanId, place.getId(), place.getVisitOrder());
        }

        return findTripPlan(userId, tripPlanId);
    }

    // 장소 삭제 후 TripPlan 객체가 남은 장소들의 visitOrder를 1부터 다시 정렬한다.
    @Transactional
    public TripPlanDetailResponseDto removePlace(Long userId, long tripPlanId, long tripPlanPlaceId) {
        TripPlan tripPlan = findOwnedTripPlanWithPlaces(userId, tripPlanId);
        tripPlan.removePlace(tripPlanPlaceId);
        tripPlanMapper.deleteTripPlanPlace(tripPlanId, tripPlanPlaceId);
        for (TripPlanPlace place : tripPlan.getPlaces()) {
            tripPlanMapper.updateTripPlanPlaceOrder(tripPlanId, place.getId(), place.getVisitOrder());
        }
        return findTripPlan(userId, tripPlanId);
    }

    // 사용자 소유의 여행 계획만 조회하도록 userId와 tripPlanId를 함께 사용한다.
    private TripPlan findOwnedTripPlan(Long userId, long tripPlanId) {
        TripPlan tripPlan = tripPlanMapper.findTripPlanByIdAndUserId(tripPlanId, requiredUserId(userId));
        if (tripPlan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip plan not found.");
        }
        return tripPlan;
    }

    // 도메인 규칙을 실행해야 하는 변경 작업에서는 계획에 포함된 장소 목록까지 조립한다.
    private TripPlan findOwnedTripPlanWithPlaces(Long userId, long tripPlanId) {
        TripPlan tripPlan = findOwnedTripPlan(userId, tripPlanId);
        tripPlan.loadPlaces(tripPlanMapper.findTripPlanPlacesByTripPlanId(tripPlanId));
        return tripPlan;
    }

    // 로그인 연동 전까지는 userId를 요청 파라미터로 받으므로 null을 명시적으로 막는다.
    private long requiredUserId(Long userId) {
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is required.");
        }
        return userId;
    }
}
