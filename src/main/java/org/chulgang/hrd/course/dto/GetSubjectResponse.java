package org.chulgang.hrd.course.dto;

import org.chulgang.hrd.course.domain.Subject;
import org.chulgang.hrd.util.FormatConverter;

public class GetSubjectResponse {
    private Long id;
    private String name;
    String createdAt;
    String modifiedAt;

    private GetSubjectResponse(Long id, String name, String createdAt, String modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static GetSubjectResponse from(Subject subject) {
        String createdAt = null;
        if (subject.getCreatedAt() != null) {
            createdAt = FormatConverter.parseToString(subject.getCreatedAt());
        }

        String modifiedAt = null;
        if (subject.getModifiedAt() != null) {
            modifiedAt = FormatConverter.parseToString(subject.getModifiedAt());
        }

        return new GetSubjectResponse(subject.getId(), subject.getName(), createdAt, modifiedAt);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
