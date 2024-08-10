package org.chulgang.hrd.classroom.model.service;

import org.chulgang.hrd.classroom.dto.GetTimePeriodResponse;
import org.chulgang.hrd.classroom.dto.GetTimePeriodsResponse;
import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;

import java.time.LocalDate;

public interface TimePeriodService {
    GetTimePeriodsResponse getUsableTimePeriodsByClassroomId(Long classroomId, LocalDate startDate, LocalDate lastDate);

    GetTimePeriodResponse getTimePeriod(Long id);

    void updateUsedTimePeriod(UpdateUsedTimePeriodRequest updateUsedTimePeriodRequest);
}
