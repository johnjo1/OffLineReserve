package org.chulgang.hrd.payment.domain;

import java.time.LocalDateTime;

public class PayedCourse {

    private Long id;
    private Long courseId;
    private Long reservationId;
    private LocalDateTime modifiedAt;
    private LocalDateTime payedAt;
    private int payedAmount;
    private boolean isRefunded;
    private LocalDateTime refundedAt;
    private boolean isExpired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public LocalDateTime getPayedAt() {
        return payedAt;
    }

    public void setPayedAt(LocalDateTime payedAt) {
        this.payedAt = payedAt;
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

    public LocalDateTime getRefundedAt() {
        return refundedAt;
    }

    public void setRefundedAt(LocalDateTime refundedAt) {
        this.refundedAt = refundedAt;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
