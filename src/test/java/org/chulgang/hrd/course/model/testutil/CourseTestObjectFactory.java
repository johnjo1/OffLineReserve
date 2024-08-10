package org.chulgang.hrd.course.model.testutil;

import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.course.dto.GetCourseResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CourseTestObjectFactory extends Course {
    public static Course createCourse(
            Long id, Long subjectId, Long teacherId, Long timePeriodId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate
    ) {
        return Course.of(id, subjectId, teacherId, timePeriodId, name, description, price, startDate, lastDate);
    }

    public static Course createCourse(
            Long id, Long subjectId, Long teacherId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return Course.of(
                id, subjectId, teacherId, name, description, price,
                startDate, lastDate, averageScore, remainedSeat, createdAt, modifiedAt
        );
    }

    public static Course createCourse(
            Long id, String name, String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return Course.of(
                id, name, description, price, startDate, lastDate, averageScore, remainedSeat, createdAt, modifiedAt
        );
    }

    public static CreateCourseRequest createCourseRequest(
            Long subjectId, Long teacherId, Long timePeriodId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate
    ) {
        return CreateCourseRequest.of(
                subjectId, teacherId, timePeriodId, name, description, price, startDate, lastDate, 50
        );
    }

    public static GetCourseResponse createCourseResponse(
            Long id, Long subjectId, String subjectName, Long teacherId, String teacherName, Long timePeriodId,
            String name, String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return GetCourseResponse.of(
                id, subjectId, subjectName, teacherId, teacherName, timePeriodId, name, description, price,
                startDate, lastDate, averageScore, remainedSeat, createdAt, modifiedAt
        );
    }
}
