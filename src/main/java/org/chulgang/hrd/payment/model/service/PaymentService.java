package org.chulgang.hrd.payment.model.service;

import org.chulgang.hrd.payment.dto.ExecutePaymentRequest;
import org.chulgang.hrd.payment.dto.PaidCourseDetailResponse;
import org.chulgang.hrd.payment.dto.PaymentCardResponse;

import java.util.List;

public interface PaymentService {

    boolean executePayment(ExecutePaymentRequest executePaymentRequest);

    List<PaymentCardResponse> getPagedPayments(Long userId, int pageNumber);

    PaidCourseDetailResponse getPaidCourseDetail(Long courseId);

    int getTotalPayments(Long userId);

    boolean refundPayment(Long userId, Long payedCourseId);
}
