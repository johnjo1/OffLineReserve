package org.chulgang.hrd.payment.model.repository;

import org.chulgang.hrd.payment.domain.PayedCourse;
import org.chulgang.hrd.payment.dto.PaymentCardResponse;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    List<PaymentCardResponse> findPaymentCourseCardByMemberId(Long userId, int pageNumber);

    Optional<PayedCourse> findPayedCourseById(Long payedCourseId);

    int countPayments(Long userId);

    void insertPayedCourse(Long userId, Long reservationId, Long courseId, int paymentAmount);

    void updatePayedCourseRefundStatus(Long payedCourseId, boolean isRefunded);
}
