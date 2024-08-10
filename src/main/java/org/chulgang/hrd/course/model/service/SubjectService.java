package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.course.dto.GetSubjectsResponse;

public interface SubjectService {
    GetSubjectsResponse getSubjects();

    String getSubjectName(Long id);
}
