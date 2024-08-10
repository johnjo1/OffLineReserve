package org.chulgang.hrd.course.dto;

import org.chulgang.hrd.course.domain.Subject;

import java.util.ArrayList;
import java.util.List;

public class GetSubjectsResponse {
    private List<GetSubjectResponse> getCourseResponses;

    private GetSubjectsResponse(List<GetSubjectResponse> getCourseResponses) {
        this.getCourseResponses = getCourseResponses;
    }

    public List<GetSubjectResponse> getCourseResponses() {
        return getCourseResponses;
    }

    public GetSubjectResponse get(int idx) {
        return getCourseResponses.get(idx);
    }

    public int size() {
        return getCourseResponses.size();
    }

    public static GetSubjectsResponse from(List<Subject> subjects) {
        List<GetSubjectResponse> getSubjectResponses = new ArrayList<>();
        for (Subject subject : subjects) {
            getSubjectResponses.add(GetSubjectResponse.from(subject));
        }

        return new GetSubjectsResponse(getSubjectResponses);
    }
}
