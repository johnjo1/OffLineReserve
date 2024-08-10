package org.chulgang.hrd.course.dto;

import org.chulgang.hrd.classroom.domain.Period;
import org.chulgang.hrd.classroom.dto.GetTimePeriodResponse;

public class CourseResponseForPayment {
    private String subjectName;
    private String courseName;
    private String teacherName;
    private String classroomName;
    private String courseDescription;
    private int price;
    private String startDate;
    private String lastDate;
    private Period period;
    private int remainedSeat;
    private float averageScore;

    private CourseResponseForPayment(
            String subjectName, String courseName, String teacherName,
            String classroomName, String courseDescription, int price, String startDate,
            String lastDate, Period period, int remainedSeat, float averageScore
    ) {
        this.subjectName = subjectName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.classroomName = classroomName;
        this.courseDescription = courseDescription;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.period = period;
        this.remainedSeat = remainedSeat;
        this.averageScore = averageScore;
    }

    public static CourseResponseForPayment from(
            GetCourseResponse getCourseResponse, String subjectName, String teacherName,
            GetTimePeriodResponse getTimePeriodResponse, String classroomName
    ) {
        return new CourseResponseForPayment(
                subjectName,
                getCourseResponse.getName(),
                teacherName,
                classroomName,
                getCourseResponse.getDescription(),
                getCourseResponse.getPrice(),
                getCourseResponse.getStartDate(),
                getCourseResponse.getLastDate(),
                getTimePeriodResponse.getPeriod(),
                getCourseResponse.getRemainedSeat(),
                getCourseResponse.getAverageScore()
        );
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public Period getPeriod() {
        return period;
    }

    public int getRemainedSeat() {
        return remainedSeat;
    }

    public float getAverageScore() {
        return averageScore;
    }
}
