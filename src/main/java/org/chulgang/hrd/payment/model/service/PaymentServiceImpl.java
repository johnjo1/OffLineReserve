package org.chulgang.hrd.payment.model.service;

import org.chulgang.hrd.course.dto.CourseResponseForPayment;
import org.chulgang.hrd.course.model.service.CoursePaymentService;
import org.chulgang.hrd.course.model.service.CoursePaymentServiceImpl;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.CourseServiceImpl;
import org.chulgang.hrd.payment.domain.PayedCourse;
import org.chulgang.hrd.payment.dto.ExecutePaymentRequest;
import org.chulgang.hrd.payment.dto.PaidCourseDetailResponse;
import org.chulgang.hrd.payment.dto.PaymentCardResponse;
import org.chulgang.hrd.payment.model.repository.PaymentRepository;
import org.chulgang.hrd.payment.model.repository.PaymentRepositoryImpl;
import org.chulgang.hrd.reservation.model.service.ReservationService;
import org.chulgang.hrd.reservation.model.service.ReservationServiceImpl;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryService;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {
    private static final PaymentServiceImpl INSTANCE = new PaymentServiceImpl(
            PaymentRepositoryImpl.getInstance(), WalletHistoryServiceImpl.getInstance(),
            ReservationServiceImpl.getInstance(), CourseServiceImpl.getInstance(), CoursePaymentServiceImpl.getInstance());
    private final PaymentRepository paymentRepository;
    private final WalletHistoryService walletHistoryService;
    private final ReservationService reservationService;
    private final CourseService courseService;
    private final CoursePaymentService coursePaymentService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, WalletHistoryService walletHistoryService, ReservationService reservationService,
                              CourseService courseService, CoursePaymentService coursePaymentService) {
        this.paymentRepository = paymentRepository;
        this.walletHistoryService = walletHistoryService;
        this.reservationService = reservationService;
        this.courseService = courseService;
        this.coursePaymentService = coursePaymentService;
    }

    public static PaymentServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean executePayment(ExecutePaymentRequest executePaymentRequest) {
        int currentAmount = walletHistoryService.currentAmountByUser(executePaymentRequest.getUserId());
        if (currentAmount <= executePaymentRequest.getPaymentAmount()) {
            throw new IllegalArgumentException("잔액이 부족합니다. 잔액 : " + executePaymentRequest.getPaymentAmount());
        }
        int remainedSeat = courseService.getRemainedSeat(executePaymentRequest.getCourseId());
        if (remainedSeat <= 0) {
            throw new IllegalArgumentException("남은자리가 없습니다.");
        }

        walletHistoryService.deductFromWallet(executePaymentRequest.getUserId(), executePaymentRequest.getPaymentAmount());
        paymentRepository.insertPayedCourse(executePaymentRequest.getUserId(), executePaymentRequest.getReservationId(), executePaymentRequest.getCourseId(), executePaymentRequest.getPaymentAmount());
        courseService.updateRemainedSeat( executePaymentRequest.getCourseId(), remainedSeat - 1);
        reservationService.updateReservationStatus( executePaymentRequest.getReservationId(), 1);
        return true;
    }

    @Override
    public List<PaymentCardResponse> getPagedPayments(Long userId, int pageNumber) {
        return paymentRepository.findPaymentCourseCardByMemberId(userId, pageNumber);
    }

    @Override
    public PaidCourseDetailResponse getPaidCourseDetail(Long courseId) {
        Optional<PayedCourse> payedCourseOptional = paymentRepository.findPayedCourseById(courseId);
        if (payedCourseOptional.isPresent()) {
            PayedCourse payedCourse = payedCourseOptional.get();
            CourseResponseForPayment courseResponseForPayment = coursePaymentService.getCourseForPayment(courseId);
            return buildPaidCourseDetailResponse(payedCourse, courseResponseForPayment);
        }
        return new PaidCourseDetailResponse();
    }

    @Override
    public int getTotalPayments(Long userId) {
        return (int) Math.ceil((double)  paymentRepository.countPayments(userId) / 10);
    }

    @Override
    public boolean refundPayment(Long userId, Long payedCourseId) {
        PayedCourse payedCourse = paymentRepository.findPayedCourseById(payedCourseId)
                .orElseThrow(() -> new IllegalArgumentException("강좌 없음"));
        if (payedCourse.isRefunded()) {
            throw new IllegalArgumentException("이미 환불된 강좌입니다.");
        }
        walletHistoryService.refundWallet(userId, payedCourse.getPayedAmount());
        paymentRepository.updatePayedCourseRefundStatus(payedCourseId, true);
        courseService.updateRemainedSeat(payedCourse.getCourseId(),
                courseService.getRemainedSeat(payedCourse.getCourseId()) + 1);
        reservationService.updateReservationStatus(payedCourse.getReservationId(), 0);
        return true;
    }

    private PaidCourseDetailResponse buildPaidCourseDetailResponse(PayedCourse payedCourse, CourseResponseForPayment courseResponseForPayment) {
        PaidCourseDetailResponse response = new PaidCourseDetailResponse();
        response.setPaymentId(payedCourse.getId());
        response.setCourseId(payedCourse.getCourseId());
        response.setReservationId(payedCourse.getReservationId());
        response.setSubjectName(courseResponseForPayment.getSubjectName());
        response.setCourseName(courseResponseForPayment.getCourseName());
        response.setDescription(courseResponseForPayment.getCourseDescription());
        response.setPrice(courseResponseForPayment.getPrice());
        response.setStartDate(parseDate(courseResponseForPayment.getStartDate()));
        response.setLastDate(parseDate(courseResponseForPayment.getLastDate()));
        response.setPeriod(courseResponseForPayment.getPeriod().getDescription());
        response.setClassRoomName(courseResponseForPayment.getClassroomName());
        response.setAverageScore(courseResponseForPayment.getAverageScore());
        response.setIsRefunded(payedCourse.isRefunded() ? 1 : 0);
        return response;
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
