package org.chulgang.hrd.course.dto;

import org.chulgang.hrd.course.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class GetCoursesResponse {
    private List<GetCourseResponse> getCourseResponses;
    private int pageCount;

    public GetCoursesResponse() {
        getCourseResponses = new ArrayList<>();
    }

    private GetCoursesResponse(List<GetCourseResponse> getCourseResponses, int pageCount) {
        this.getCourseResponses = getCourseResponses;
        this.pageCount = pageCount;
    }

    public List<GetCourseResponse> getCourseResponses() {
        return getCourseResponses;
    }

    public GetCourseResponse get(int idx) {
        return getCourseResponses.get(idx);
    }

    public int size() {
        return getCourseResponses.size();
    }

    public int getPageCount() {
        return pageCount;
    }

    public static GetCoursesResponse from(List<Course> courses, int pageCount) {
        List<GetCourseResponse> getCourseResponses = new ArrayList<GetCourseResponse>();

        for (Course course : courses) {
            getCourseResponses.add(GetCourseResponse.from(course));
        }

        return new GetCoursesResponse(getCourseResponses, pageCount);
    }

    public void add(GetCourseResponse getCourseResponse) {
        getCourseResponses.add(getCourseResponse);
    }
}
