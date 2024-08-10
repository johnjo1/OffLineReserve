package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.course.dto.GetCourseResponse;
import org.chulgang.hrd.course.dto.GetCoursesResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;

public interface CourseService {
    GetCoursesResponse getCourses(int size, int pageNumber, SubjectService subjectService, UsersService usersService);

    GetCourseResponse getCourse(Long id, SubjectService subjectService, UsersService usersService);

    boolean create(CreateCourseRequest createCourseRequest, TimePeriodService timePeriodService);

    boolean checkDuplicateCourseName(String courseName);

    int getRemainedSeat(Long id);

    void updateRemainedSeat(Long id, int newRemainedSeat);

    int getPageCount(int size);
}
