package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.classroom.dto.GetTimePeriodResponse;
import org.chulgang.hrd.classroom.model.service.ClassroomService;
import org.chulgang.hrd.classroom.model.service.ClassroomServiceImpl;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.classroom.model.service.TimePeriodServiceImpl;
import org.chulgang.hrd.course.dto.CourseResponseForPayment;
import org.chulgang.hrd.course.dto.GetCourseResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;

public class CoursePaymentServiceImpl implements CoursePaymentService {
    private static final CoursePaymentService INSTANCE = new CoursePaymentServiceImpl();

    public static CoursePaymentService getInstance() {
        return INSTANCE;
    }

    @Override
    public CourseResponseForPayment getCourseForPayment(Long courseId) {
        courseId = 5L;
        CourseService courseService = CourseServiceImpl.getInstance();
        SubjectService subjectService = SubjectServiceImpl.getInstance();
        UsersService usersService = UsersService.getInstance();
        TimePeriodService timePeriodService = TimePeriodServiceImpl.getInstance();
        ClassroomService classroomService = ClassroomServiceImpl.getInstance();

        GetCourseResponse getCourseResponse = courseService.getCourse(courseId, SubjectServiceImpl.getInstance(), UsersService.getInstance());
        String subjectName = subjectService.getSubjectName(getCourseResponse.getSubjectId());
        String teacherName = usersService.findById(getCourseResponse.getTeacherId());
        GetTimePeriodResponse getTimePeriodResponse
                = timePeriodService.getTimePeriod(getCourseResponse.getTimePeriodId());
        String classroomName = classroomService.getName(getTimePeriodResponse.getClassroomId());

        return CourseResponseForPayment.from(
                getCourseResponse, subjectName, teacherName, getTimePeriodResponse, classroomName
        );
    }
}
