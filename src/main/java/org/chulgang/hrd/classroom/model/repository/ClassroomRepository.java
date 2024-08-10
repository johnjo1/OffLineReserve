package org.chulgang.hrd.classroom.model.repository;

import org.chulgang.hrd.classroom.domain.Classroom;

import java.util.List;

public interface ClassroomRepository {
    List<Classroom> findAll();

    String findNameById(Long id);
}
