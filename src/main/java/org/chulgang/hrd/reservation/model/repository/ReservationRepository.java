package org.chulgang.hrd.reservation.model.repository;

import org.chulgang.hrd.reservation.dto.ReservationCardResponse;
import org.chulgang.hrd.reservation.entity.ReservationCourse;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    boolean registerReservation(Long memberId, Long courseId);

    List<ReservationCardResponse> findReservationCardByMemberId(Long memberId, int pageNumber);

    boolean deleteReservation(Long reservationId);

    int countReservations(Long userId);

    Optional<ReservationCourse> findById(Long id);

    void updateReservationStatus(Long reservationId, int status);

    boolean isAlreadyReserved(Long userId, Long courseId);
}
