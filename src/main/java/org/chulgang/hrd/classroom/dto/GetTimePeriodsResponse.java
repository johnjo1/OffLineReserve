package org.chulgang.hrd.classroom.dto;

import org.chulgang.hrd.classroom.domain.TimePeriod;

import java.util.ArrayList;
import java.util.List;

public class GetTimePeriodsResponse {
    private List<GetTimePeriodResponse> getTimePeriodResponses;

    private GetTimePeriodsResponse(List<GetTimePeriodResponse> getTimePeriodResponses) {
        this.getTimePeriodResponses = getTimePeriodResponses;
    }

    public List<GetTimePeriodResponse> getTimePeriodResponses() {
        return getTimePeriodResponses;
    }

    public GetTimePeriodResponse get(int idx) {
        return getTimePeriodResponses.get(idx);
    }

    public int size() {
        return getTimePeriodResponses.size();
    }

    public static GetTimePeriodsResponse from(List<TimePeriod> timePeriods) {
        List<GetTimePeriodResponse> getTimePeriodResponses = new ArrayList<>();
        for (TimePeriod timePeriod : timePeriods) {
            getTimePeriodResponses.add(GetTimePeriodResponse.from(timePeriod));
        }

        return new GetTimePeriodsResponse(getTimePeriodResponses);
    }
}
