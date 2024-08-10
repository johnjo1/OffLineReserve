package org.chulgang.hrd.classroom.model.service;

import org.chulgang.hrd.classroom.domain.TimePeriod;
import org.chulgang.hrd.classroom.dto.GetTimePeriodResponse;
import org.chulgang.hrd.classroom.dto.GetTimePeriodsResponse;
import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;
import org.chulgang.hrd.classroom.model.repository.TimePeriodRepository;
import org.chulgang.hrd.classroom.model.repository.TimePeriodRepositoryImpl;

import java.time.LocalDate;

public class TimePeriodServiceImpl implements TimePeriodService {
    private static final TimePeriodService INSTANCE = new TimePeriodServiceImpl(TimePeriodRepositoryImpl.getInstance());
    private final TimePeriodRepository timePeriodRepository;

    public TimePeriodServiceImpl(TimePeriodRepository timePeriodRepository) {
        this.timePeriodRepository = timePeriodRepository;
    }

    public static TimePeriodService getInstance() {
        return INSTANCE;
    }

    @Override
    public GetTimePeriodsResponse getUsableTimePeriodsByClassroomId(
            Long classroomId, LocalDate startDate, LocalDate lastDate
    ) {
        return GetTimePeriodsResponse.from(
                timePeriodRepository.findUsableTimesByClassroomId(classroomId, startDate, lastDate)
        );
    }

    @Override
    public GetTimePeriodResponse getTimePeriod(Long id) {
        return GetTimePeriodResponse.from(timePeriodRepository.findById(id));
    }

    @Override
    public void updateUsedTimePeriod(UpdateUsedTimePeriodRequest updateUsedTimePeriodRequest) {
        TimePeriod timePeriod = timePeriodRepository.findById(updateUsedTimePeriodRequest.getId());
        if (updateUsedTimePeriodRequest.updateDates(timePeriod)) {
            timePeriodRepository.updateUsedTimePeriodById(updateUsedTimePeriodRequest);
        }
    }
}
