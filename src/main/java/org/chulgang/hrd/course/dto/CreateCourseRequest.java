package org.chulgang.hrd.course.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.util.FormatConverter;

import java.time.LocalDate;

import static org.chulgang.hrd.course.util.RequestConstant.LOGIN_SESSION_ATTRIBUTE_NAME;

public class CreateCourseRequest {
    private Long subjectId;
    private Long teacherId;
    private Long timePeriodId;
    private String name;
    private String description;
    private int price;
    private LocalDate startDate;
    private LocalDate lastDate;
    private int remainedSeat;

    private CreateCourseRequest(
            Long subjectId, Long teacherId, Long timePeriodId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate, int remainedSeat
    ) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.timePeriodId = timePeriodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.remainedSeat = remainedSeat;
    }

    public static CreateCourseRequest from(HttpServletRequest request) {
        UsersLoginResponse usersLoginResponse
                = (UsersLoginResponse) request.getSession().getAttribute(LOGIN_SESSION_ATTRIBUTE_NAME);

        return new CreateCourseRequest(
                Long.parseLong(request.getParameter("subjectId")),
                usersLoginResponse.getId(),
                Long.parseLong(request.getParameter("time-period")),
                request.getParameter("courseName"),
                request.getParameter("course-description"),
                FormatConverter.parseToInt(request.getParameter("price")),
                FormatConverter.parseToDate(request.getParameter("start-date")),
                FormatConverter.parseToDate(request.getParameter("last-date")),
                FormatConverter.parseToInt(request.getParameter("seatCount"))
        );
    }

    public static CreateCourseRequest of(Long subjectId, Long teacherId, Long timePeriodId, String name,
                                         String description, int price,
                                         LocalDate startDate, LocalDate lastDate, int remainedSeat) {
        return new CreateCourseRequest(
                subjectId, teacherId, timePeriodId, name, description, price, startDate, lastDate, remainedSeat
        );
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getTimePeriodId() {
        return timePeriodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedSeat() {
        return remainedSeat;
    }
}