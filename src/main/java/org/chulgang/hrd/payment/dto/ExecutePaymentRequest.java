package org.chulgang.hrd.payment.dto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

public class ExecutePaymentRequest {

    Long userId;
    Long reservationId;
    Long courseId;
    int paymentAmount;

    public ExecutePaymentRequest(Long userId, Long reservationId, Long courseId, int paymentAmount) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.courseId = courseId;
        this.paymentAmount = paymentAmount;
    }

    public static ExecutePaymentRequest from(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");
        Long userId = user.getId();
        Long reservationId = Long.parseLong(request.getParameter("reservationId"));
        Long courseId = Long.parseLong(request.getParameter("courseId"));
        int paymentAmount = Integer.parseInt(request.getParameter("paymentAmount"));
        return new ExecutePaymentRequest(userId, reservationId, courseId, paymentAmount);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        reservationId = reservationId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
