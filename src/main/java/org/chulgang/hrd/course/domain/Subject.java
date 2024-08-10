package org.chulgang.hrd.course.domain;

import org.chulgang.hrd.util.FormatConverter;

import java.time.LocalDateTime;

public class Subject {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Subject(Long id, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Subject from(String[] data) {
        LocalDateTime modifiedAt = data[3] == null ? null : FormatConverter.parseToDateTime(data[3]);

        return new Subject(
                FormatConverter.parseToLong(data[0]),
                data[1],
                FormatConverter.parseToDateTime(data[2]),
                modifiedAt
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
