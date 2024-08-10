package org.chulgang.hrd.reservation.model.service;

import org.chulgang.hrd.course.dto.CourseResponseForPayment;
import org.chulgang.hrd.course.model.service.CoursePaymentService;
import org.chulgang.hrd.course.model.service.CoursePaymentServiceImpl;
import org.chulgang.hrd.reservation.dto.ReservationCardResponse;
import org.chulgang.hrd.reservation.dto.ReservationCourseDetailResponse;
import org.chulgang.hrd.reservation.entity.ReservationCourse;
import org.chulgang.hrd.reservation.model.repository.ReservationRepository;
import org.chulgang.hrd.reservation.model.repository.ReservationRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    private static final ReservationServiceImpl INSTANCE = new ReservationServiceImpl(ReservationRepositoryImpl.getInstance(), CoursePaymentServiceImpl.getInstance());
    private final ReservationRepository reservationRepository;
    private final CoursePaymentService coursePaymentService;

    private ReservationServiceImpl(ReservationRepository reservationRepository, CoursePaymentService coursePaymentService) {
        this.reservationRepository = reservationRepository;
        this.coursePaymentService = coursePaymentService;
    }

    public static ReservationServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean registerReservation(Long userId, Long courseId) {
        return reservationRepository.registerReservation(userId, courseId);
    }

    @Override
    public List<ReservationCardResponse> getPagedReservations(Long memberId, int pageNumber) {
        return reservationRepository.findReservationCardByMemberId(memberId, pageNumber);
    }

    @Override
    public ReservationCourseDetailResponse getReservationCourseDetail(Long id) {
        Optional<ReservationCourse> reservationCourseOptional = reservationRepository.findById(id);
        if (reservationCourseOptional.isPresent()) {
            ReservationCourse reservationCourse = reservationCourseOptional.get();
            CourseResponseForPayment courseResponseForPayment = coursePaymentService.getCourseForPayment(reservationCourse.getCourseId());
            return buildReservationCourseDetailResponse(reservationCourse, courseResponseForPayment);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteReservation(Long reservationCourseId) {
        return reservationRepository.deleteReservation(reservationCourseId);
    }

    @Override
    public int getTotalReservations(Long userId) {
        return (int) Math.ceil((double) reservationRepository.countReservations(userId) / 10);
    }

    @Override
    public void updateReservationStatus(Long reservationId, int status) {
        reservationRepository.updateReservationStatus(reservationId, status);
    }

    @Override
    public boolean isAlreadyReserved(Long userId, Long courseId) {
        return reservationRepository.isAlreadyReserved(userId, courseId);
    }

    private ReservationCourseDetailResponse buildReservationCourseDetailResponse(ReservationCourse reservationCourse, CourseResponseForPayment courseResponseForPayment) {
        ReservationCourseDetailResponse response = new ReservationCourseDetailResponse();
        response.setReservationId(reservationCourse.getReservationId());
        response.setReservationCourseId(reservationCourse.getId());
        response.setCourseId(reservationCourse.getCourseId());
        response.setCourseName(courseResponseForPayment.getCourseName());
        response.setSubjectName(courseResponseForPayment.getSubjectName());
        response.setTeacherName(courseResponseForPayment.getTeacherName());
        response.setStartDate(parseDate(courseResponseForPayment.getStartDate()));
        response.setLastDate(parseDate(courseResponseForPayment.getLastDate()));
        response.setPrice(courseResponseForPayment.getPrice());
        response.setPeriod(courseResponseForPayment.getPeriod().getDescription());
        response.setRoomName(courseResponseForPayment.getClassroomName());
        response.setAverageScore(courseResponseForPayment.getAverageScore());
        response.setDescription(courseResponseForPayment.getCourseDescription());
        return response;
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
