package com.ssafy.history.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.history.trip.domain.TripPlan;
import com.ssafy.history.trip.domain.TripPlanPlace;
import com.ssafy.history.trip.dto.TripPlanPlaceResponseDto;

public interface TripPlanMapper {
    int insertTripPlan(TripPlan tripPlan);

    List<TripPlan> findTripPlansByUserId(
            @Param("userId") long userId,
            @Param("limit") int limit,
            @Param("offset") int offset);

    TripPlan findTripPlanByIdAndUserId(
            @Param("tripPlanId") long tripPlanId,
            @Param("userId") long userId);

    int updateTripPlan(TripPlan tripPlan);

    int deleteTripPlanByIdAndUserId(
            @Param("tripPlanId") long tripPlanId,
            @Param("userId") long userId);

    int insertTripPlanPlace(
            @Param("tripPlanId") long tripPlanId,
            @Param("place") TripPlanPlace place);

    List<TripPlanPlace> findTripPlanPlacesByTripPlanId(@Param("tripPlanId") long tripPlanId);

    List<TripPlanPlaceResponseDto> findTripPlanPlaceResponsesByTripPlanId(@Param("tripPlanId") long tripPlanId);

    int updateTripPlanPlaceVisitInfo(
            @Param("tripPlanId") long tripPlanId,
            @Param("place") TripPlanPlace place);

    int updateTripPlanPlaceOrder(
            @Param("tripPlanId") long tripPlanId,
            @Param("tripPlanPlaceId") long tripPlanPlaceId,
            @Param("visitOrder") int visitOrder);

    int deleteTripPlanPlace(
            @Param("tripPlanId") long tripPlanId,
            @Param("tripPlanPlaceId") long tripPlanPlaceId);

    Integer findNextVisitOrder(@Param("tripPlanId") long tripPlanId);

    int countTripPlanPlacesByTripPlanId(@Param("tripPlanId") long tripPlanId);

    boolean existsTripPlanPlace(
            @Param("tripPlanId") long tripPlanId,
            @Param("placeId") long placeId);
}
