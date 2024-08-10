package org.chulgang.hrd.classroom.model.repository;

import org.chulgang.hrd.classroom.domain.TimePeriod;
import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;

import java.time.LocalDate;
import java.util.List;

public interface TimePeriodRepository {
    List<TimePeriod> findUsableTimesByClassroomId(Long classroomId, LocalDate startDate, LocalDate lastDate);

    TimePeriod findById(Long id);

    void updateUsedTimePeriodById(UpdateUsedTimePeriodRequest updateUsedTimePeriodRequest);
}
