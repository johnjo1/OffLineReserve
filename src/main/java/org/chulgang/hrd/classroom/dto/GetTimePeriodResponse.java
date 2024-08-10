package org.chulgang.hrd.classroom.dto;

import org.chulgang.hrd.classroom.domain.Period;
import org.chulgang.hrd.classroom.domain.TimePeriod;
import org.chulgang.hrd.util.FormatConverter;

public class GetTimePeriodResponse {
    private Long id;
    private Long classroomId;
    private Period period;
    private Boolean isUsed;
    private String createdAt;
    private String modifiedAt;

    private GetTimePeriodResponse(
            Long id, Long classroomId, Period period, Boolean isUsed, String createdAt, String modifiedAt
    ) {
        this.id = id;
        this.classroomId = classroomId;
        this.period = period;
        this.isUsed = isUsed;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetTimePeriodResponse from(TimePeriod timePeriod) {
        String createdAt = null;
        if (timePeriod.getCreatedAt() != null) {
            createdAt = FormatConverter.parseToString(timePeriod.getCreatedAt());
        }

        String modifiedAt = null;
        if (timePeriod.getModifiedAt() != null) {
            modifiedAt = FormatConverter.parseToString(timePeriod.getModifiedAt());
        }

        return new GetTimePeriodResponse(
                timePeriod.getId(),
                timePeriod.getClassroomId(),
                timePeriod.getPeriod(),
                FormatConverter.parseToBoolean(timePeriod.isUsed()),
                createdAt,
                modifiedAt
        );
    }

    public Long getId() {
        return id;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public Period getPeriod() {
        return period;
    }

    public Boolean isUsed() {
        return isUsed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }
}
