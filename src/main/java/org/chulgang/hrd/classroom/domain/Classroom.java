package org.chulgang.hrd.classroom.domain;

import org.chulgang.hrd.util.FormatConverter;

import java.time.LocalDateTime;

public class Classroom {
    private Long id;
    private String name;
    private int seatCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Classroom(Long id, String name, int seatCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.seatCount = seatCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Classroom from(String[] data) {
        LocalDateTime modifiedAt = data[4] == null ? null : FormatConverter.parseToDateTime(data[4]);

        return new Classroom(
                FormatConverter.parseToLong(data[0]),
                data[1],
                FormatConverter.parseToInt(data[2]),
                FormatConverter.parseToDateTime(data[3]),
                modifiedAt
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
