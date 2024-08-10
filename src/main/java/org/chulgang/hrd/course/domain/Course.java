package org.chulgang.hrd.course.domain;


import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.util.FormatConverter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Course {
    private Long id;
    private Long subjectId;
    private Long teacherId;
    private Long timePeriodId;
    private String name;
    private String description;
    private int price;
    private LocalDate startDate;
    private LocalDate lastDate;
    private float averageScore;
    private int remainedSeat;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Course() {
    }

    private Course(
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

    private Course(
            Long id, String name, String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.averageScore = averageScore;
        this.remainedSeat = remainedSeat;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    private Course(
            Long id, Long subjectId, Long teacherId, Long timePeriodId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate
    ) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.timePeriodId = timePeriodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
    }

    private Course(
            Long id, Long subjectId, Long teacherId, Long timePeriodId, String name, String description,
            int price, LocalDate startDate, LocalDate lastDate, float averageScore, int remainedSeat,
            LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.timePeriodId = timePeriodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.averageScore = averageScore;
        this.remainedSeat = remainedSeat;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    private Course(
            Long id, Long subjectId, Long teacherId, String name, String description,
            int price, LocalDate startDate, LocalDate lastDate, float averageScore, int remainedSeat,
            LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.timePeriodId = timePeriodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.averageScore = averageScore;
        this.remainedSeat = remainedSeat;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Course from(CreateCourseRequest createCourseRequest) {
        return new Course(
                createCourseRequest.getSubjectId(),
                createCourseRequest.getTeacherId(),
                createCourseRequest.getTimePeriodId(),
                createCourseRequest.getName(),
                createCourseRequest.getDescription(),
                createCourseRequest.getPrice(),
                createCourseRequest.getStartDate(),
                createCourseRequest.getLastDate(),
                createCourseRequest.getRemainedSeat()
        );
    }

    public static Course from(String[] data) {
        LocalDateTime modifiedAt = data[12] == null ? null : FormatConverter.parseToDateTime(data[12]);
        float averageScore = data[9] == null ? 0.0f : FormatConverter.parseToFloat(data[9]);

        return new Course(
                Long.parseLong(data[0]),
                Long.parseLong(data[1]),
                Long.parseLong(data[2]),
                Long.parseLong(data[3]),
                data[4],
                data[5],
                FormatConverter.parseToInt(data[6]),
                FormatConverter.parseToDate(data[7]),
                FormatConverter.parseToDate(data[8]),
                averageScore,
                FormatConverter.parseToInt(data[10]),
                FormatConverter.parseToDateTime(data[11]),
                modifiedAt
        );
    }

    protected static Course of(
            Long id, Long subjectId, Long teacherId, Long timePeriodId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate
    ) {
        return new Course(id, subjectId, teacherId, timePeriodId, name, description, price, startDate, lastDate);
    }

    protected static Course of(
            Long id, Long subjectId, Long teacherId, String name,
            String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return new Course(
                id, subjectId, teacherId, name, description, price, startDate,
                lastDate, averageScore, remainedSeat, createdAt, modifiedAt
        );
    }

    protected static Course of(
            Long id, String name, String description, int price, LocalDate startDate, LocalDate lastDate,
            float averageScore, int remainedSeat, LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        return new Course(id, name, description, price, startDate, lastDate, averageScore, remainedSeat, createdAt, modifiedAt);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Course course = (Course) object;
        return price == course.price
                && Float.compare(averageScore, course.averageScore) == 0
                && remainedSeat == course.remainedSeat
                && Objects.equals(id, course.id)
                && Objects.equals(subjectId, course.subjectId)
                && Objects.equals(teacherId, course.teacherId)
                && Objects.equals(timePeriodId, course.timePeriodId)
                && Objects.equals(name, course.name)
                && Objects.equals(description, course.description)
                && Objects.equals(startDate, course.startDate)
                && Objects.equals(lastDate, course.lastDate)
                && Objects.equals(modifiedAt, course.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, subjectId, teacherId, timePeriodId, name, description, price,
                startDate, lastDate, averageScore, remainedSeat, modifiedAt
        );
    }

    public void setTupleValues(PreparedStatement preparedStatement) throws SQLException {
        int index = 1;
        if (!isIdNull()) {
            preparedStatement.setLong(index++, id);
        }

        preparedStatement.setLong(index++, subjectId);
        preparedStatement.setLong(index++, teacherId);
        preparedStatement.setLong(index++, timePeriodId);
        preparedStatement.setString(index++, name);
        preparedStatement.setString(index++, description);
        preparedStatement.setInt(index++, price);
        preparedStatement.setObject(index++, startDate);
        preparedStatement.setObject(index++, lastDate);
        preparedStatement.setInt(index, remainedSeat);
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public int getRemainedSeat() {
        return remainedSeat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public boolean isIdNull() {
        return id == null;
    }
}
