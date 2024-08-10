package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.course.dto.GetCourseResponse;
import org.chulgang.hrd.course.dto.GetCoursesResponse;
import org.chulgang.hrd.course.model.repository.CourseRepository;
import org.chulgang.hrd.course.model.repository.CourseRepositoryImpl;
import org.chulgang.hrd.users.model.usersService.UsersService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private static final CourseService INSTANCE = new CourseServiceImpl(CourseRepositoryImpl.getInstance());
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static CourseService getInstance() {
        return INSTANCE;
    }

    @Override
    public GetCoursesResponse getCourses(
            int size, int pageNumber, SubjectService subjectService, UsersService usersService
    ) {
        List<Course> courses = courseRepository.findAll(size, pageNumber);
        GetCoursesResponse getCoursesResponse = new GetCoursesResponse();

        for (Course course : courses) {
            String subjectName = subjectService.getSubjectName(course.getSubjectId());
            String teacherName = usersService.findById(course.getTeacherId());

            GetCourseResponse getCourseResponse = GetCourseResponse.from(course, subjectName, teacherName);
            getCoursesResponse.add(getCourseResponse);
        }
        return getCoursesResponse;
    }

    @Override
    public GetCourseResponse getCourse(Long id, SubjectService subjectService, UsersService usersService) {
        Course course = courseRepository.findById(id);
        String subjectName = subjectService.getSubjectName(course.getSubjectId());
        String teacherName = usersService.findById(course.getTeacherId());
        return GetCourseResponse.from(course, subjectName, teacherName);
    }

    @Override
    public boolean create(CreateCourseRequest createCourseRequest, TimePeriodService timePeriodService) {
        timePeriodService.updateUsedTimePeriod(UpdateUsedTimePeriodRequest.from(createCourseRequest));

        return courseRepository.save(Course.from(createCourseRequest));
    }

    @Override
    public boolean checkDuplicateCourseName(String courseName) {
        return courseRepository.existsByName(courseName);
    }

    @Override
    public int getRemainedSeat(Long id) {
        return courseRepository.findRemainedSeatById(id);
    }

    @Override
    public void updateRemainedSeat(Long id, int newRemainedSeat) {
        courseRepository.updateRemainedSeatById(id, newRemainedSeat);
    }

    @Override
    public int getPageCount(int size) {
        return courseRepository.getPageCount(size);
    }
}
