package org.chulgang.hrd.classroom.model.service;

import org.chulgang.hrd.classroom.dto.GetClassroomsResponse;

public interface ClassroomService {
    GetClassroomsResponse getClassrooms();

    String getName(Long id);
}
