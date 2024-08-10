package org.chulgang.hrd.reservation.model.service;

import org.chulgang.hrd.reservation.dto.ReservationCardResponse;
import org.chulgang.hrd.reservation.dto.ReservationCourseDetailResponse;

import java.util.List;

public interface ReservationService {

    boolean registerReservation(Long userId, Long courseId);

    List<ReservationCardResponse> getPagedReservations(Long id , int pageNumber);

    ReservationCourseDetailResponse getReservationCourseDetail(Long courseId);

    boolean deleteReservation(Long reservationCourseId);

    int getTotalReservations(Long userId);

    void updateReservationStatus(Long reservationId, int status);

    boolean isAlreadyReserved(Long userId, Long courseId);
}
