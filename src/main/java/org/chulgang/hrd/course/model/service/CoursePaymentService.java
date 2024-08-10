package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.course.dto.CourseResponseForPayment;

public interface CoursePaymentService {
    CourseResponseForPayment getCourseForPayment(Long courseId);

}
