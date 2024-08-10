package org.chulgang.hrd.classroom.domain;

import org.chulgang.hrd.util.FormatConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimePeriod {
    private Long id;
    private Long classroomId;
    private Period period;
    private LocalDate startDate;
    private LocalDate lastDate;
    private byte isUsed;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private TimePeriod(
            Long id, Long classroomId, Period period,
            LocalDate startDate, LocalDate lastDate, byte isUsed,
            LocalDateTime createdAt, LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.classroomId = classroomId;
        this.period = period;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.isUsed = isUsed;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static TimePeriod from(String[] data) {
        LocalDateTime modifiedAt = data[7] == null ? null : FormatConverter.parseToDateTime(data[7]);

        return new TimePeriod(
                FormatConverter.parseToLong(data[0]),
                FormatConverter.parseToLong(data[1]),
                Period.valueOf(data[2]),
                FormatConverter.parseToDate(data[3]),
                FormatConverter.parseToDate(data[4]),
                FormatConverter.parseToByte(data[5]),
                FormatConverter.parseToDateTime(data[6]),
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public byte isUsed() {
        return isUsed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
