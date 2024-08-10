package org.chulgang.hrd.course.model.repository;

import org.chulgang.hrd.course.domain.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> findAll();

    String findNameById(Long id);
}
