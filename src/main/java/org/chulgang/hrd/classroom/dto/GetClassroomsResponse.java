package org.chulgang.hrd.classroom.dto;

import org.chulgang.hrd.classroom.domain.Classroom;

import java.util.ArrayList;
import java.util.List;

public class GetClassroomsResponse {
    private List<GetClassroomResponse> getClassroomResponses;

    private GetClassroomsResponse(List<GetClassroomResponse> getClassroomResponses) {
        this.getClassroomResponses = getClassroomResponses;
    }

    public List<GetClassroomResponse> getClassroomResponses() {
        return getClassroomResponses;
    }

    public GetClassroomResponse get(int idx) {
        return getClassroomResponses.get(idx);
    }

    public int size() {
        return getClassroomResponses.size();
    }

    public static GetClassroomsResponse from(List<Classroom> classrooms) {
        List<GetClassroomResponse> getClassroomResponses = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            getClassroomResponses.add(GetClassroomResponse.from(classroom));
        }

        return new GetClassroomsResponse(getClassroomResponses);
    }
}
