package org.chulgang.hrd.classroom.dto;

import org.chulgang.hrd.classroom.domain.Classroom;
import org.chulgang.hrd.util.FormatConverter;

public class GetClassroomResponse {
    private Long id;
    private String name;
    private int seatCount;
    String createdAt;
    String modifiedAt;

    private GetClassroomResponse(Long id, String name, int seatCount, String createdAt, String modifiedAt) {
        this.id = id;
        this.name = name;
        this.seatCount = seatCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetClassroomResponse from(Classroom classroom) {
        String createdAt = null;
        if (classroom.getCreatedAt() != null) {
            createdAt = FormatConverter.parseToString(classroom.getCreatedAt());
        }

        String modifiedAt = null;
        if (classroom.getModifiedAt() != null) {
            modifiedAt = FormatConverter.parseToString(classroom.getModifiedAt());
        }

        return new GetClassroomResponse(
                classroom.getId(), classroom.getName(), classroom.getSeatCount(), createdAt, modifiedAt
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
}
