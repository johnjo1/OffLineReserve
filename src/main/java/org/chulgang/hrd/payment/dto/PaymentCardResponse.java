package org.chulgang.hrd.payment.dto;

import java.time.LocalDate;

public class PaymentCardResponse {

    private Long payedCourseId;
    private Long reservationCourseId;
    private String courseName;
    private String courseDescription;
    private int payedAmount;
    private boolean isRefunded;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getPayedCourseId() {
        return payedCourseId;
    }

    public void setPayedCourseId(Long payedCourseId) {
        this.payedCourseId = payedCourseId;
    }

    public Long getReservationCourseId() {
        return reservationCourseId;
    }

    public void setReservationCourseId(Long reservationCourseId) {
        this.reservationCourseId = reservationCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(int payedAmount) {
        this.payedAmount = payedAmount;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
