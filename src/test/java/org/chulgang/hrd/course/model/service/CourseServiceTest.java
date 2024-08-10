package org.chulgang.hrd.course.model.service;

import org.chulgang.hrd.classroom.domain.TimePeriod;
import org.chulgang.hrd.classroom.model.repository.TimePeriodRepository;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.classroom.model.service.TimePeriodServiceImpl;
import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.course.dto.GetCourseResponse;
import org.chulgang.hrd.course.dto.GetCoursesResponse;
import org.chulgang.hrd.course.model.repository.CourseRepository;
import org.chulgang.hrd.course.model.testutil.CourseTestObjectFactory;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.util.FormatConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.chulgang.hrd.course.model.testutil.CourseTestConstant.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CourseServiceTest {
    CourseRepository courseRepository = mock(CourseRepository.class);
    CourseService courseService = new CourseServiceImpl(courseRepository);
    TimePeriodRepository timePeriodRepository = mock(TimePeriodRepository.class);
    TimePeriodService timePeriodService = new TimePeriodServiceImpl(timePeriodRepository);
    SubjectService subjectService = mock(SubjectService.class);
    UsersService usersService = mock(UsersService.class);
    MockedStatic<DbConnection> dbConnection = mockStatic(DbConnection.class);

    LocalDateTime now = LocalDateTime.now();
    String parsedNow = FormatConverter.parseToString(now);

    @BeforeEach
    void setUp() {
        dbConnection.when(DbConnection::initialize).thenAnswer(invocation -> null);
        when(usersService.findById(anyLong())).thenReturn(TEACHER_NAME);
    }

    @AfterEach
    void tearDown() {
        dbConnection.close();
    }

    @DisplayName("Singleton instance를 불러 올 수 있다.")
    @Test
    void getInstance() {
        // given, when
        CourseService courseService = CourseServiceImpl.getInstance();

        // then
        assertThat(courseService).isInstanceOf(CourseService.class);
    }

    @DisplayName("원하는 페이지 크기와 페이지 번호에 해당하는 강좌 목록을 조회할 수 있다.")
    @Test
    void getCourses() {
        // given
        Course course1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1,
                AVERAGE_SCORE1, REMAINED_SEAT1, now, now
        );
        Course course2 = CourseTestObjectFactory.createCourse(
                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2,
                AVERAGE_SCORE2, REMAINED_SEAT2, now, now
        );
        Course course3 = CourseTestObjectFactory.createCourse(
                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3,
                AVERAGE_SCORE3, REMAINED_SEAT3, now, now
        );

        List<Course> courses = new ArrayList<>();
        courses.addAll(List.of(course1, course2));

        when(courseRepository.findAll(anyInt(), anyInt())).thenReturn(courses);

        // when
        GetCoursesResponse getCoursesResponse1
                = courseService.getCourses(SIZE1, PAGE_NUMBER, subjectService, usersService);

        courses.add(course3);
        GetCoursesResponse getCoursesResponse2
                = courseService.getCourses(SIZE2, PAGE_NUMBER, subjectService, usersService);
        ;

        // then
        assertThat(getCoursesResponse1.getCourseResponses()).hasSize(2)
                .extracting(
                        "id", "name", "description", "price", "startDate", "lastDate",
                        "averageScore", "remainedSeat", "createdAt", "modifiedAt"
                )
                .containsExactlyInAnyOrder(
                        tuple(
                                COURSE_ID1, NAME1, DESCRIPTION1, PRICE1, PARSED_START_DATE1,
                                PARSED_LAST_DATE1, AVERAGE_SCORE1, REMAINED_SEAT1, parsedNow, parsedNow
                        ),
                        tuple(
                                COURSE_ID2, NAME2, DESCRIPTION2, PRICE2, PARSED_START_DATE2,
                                PARSED_LAST_DATE2, AVERAGE_SCORE2, REMAINED_SEAT2, parsedNow, parsedNow
                        )
                );

        assertThat(getCoursesResponse2.getCourseResponses()).hasSize(3)
                .extracting(
                        "id", "name", "description", "price", "startDate", "lastDate",
                        "averageScore", "remainedSeat", "createdAt", "modifiedAt"
                )
                .containsExactlyInAnyOrder(
                        tuple(
                                COURSE_ID1, NAME1, DESCRIPTION1, PRICE1, PARSED_START_DATE1, PARSED_LAST_DATE1,
                                AVERAGE_SCORE1, REMAINED_SEAT1, parsedNow, parsedNow
                        ),
                        tuple(
                                COURSE_ID2, NAME2, DESCRIPTION2, PRICE2, PARSED_START_DATE2, PARSED_LAST_DATE2,
                                AVERAGE_SCORE2, REMAINED_SEAT2, parsedNow, parsedNow
                        ),
                        tuple(
                                COURSE_ID3, NAME3, DESCRIPTION3, PRICE3, PARSED_START_DATE3, PARSED_LAST_DATE3,
                                AVERAGE_SCORE3, REMAINED_SEAT3, parsedNow, parsedNow
                        )
                );
    }

    @DisplayName("강좌 ID를 통해 원하는 강좌를 조회할 수 있다.")
    @Test
    void getCourse() {
        // given
        when(courseRepository.findById(anyLong())).thenReturn(
                CourseTestObjectFactory.createCourse(
                        COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1,
                        AVERAGE_SCORE1, REMAINED_SEAT1, now, now
                )
        );

        when(subjectService.getSubjectName(anyLong())).thenReturn(SUBJECT_NAME);

        // when
        GetCourseResponse getCourseResponse = courseService.getCourse(COURSE_ID1, subjectService, usersService);

        // then
        assertThat(getCourseResponse).isNotNull();
        assertThat(getCourseResponse.getId()).isEqualTo(COURSE_ID1);
        assertThat(getCourseResponse.getName()).isEqualTo(NAME1);
        assertThat(getCourseResponse.getDescription()).isEqualTo(DESCRIPTION1);
        assertThat(getCourseResponse.getPrice()).isEqualTo(PRICE1);
        assertThat(getCourseResponse.getStartDate()).isEqualTo(PARSED_START_DATE1);
        assertThat(getCourseResponse.getLastDate()).isEqualTo(PARSED_LAST_DATE1);
        assertThat(getCourseResponse.getAverageScore()).isEqualTo(AVERAGE_SCORE1);
        assertThat(getCourseResponse.getRemainedSeat()).isEqualTo(REMAINED_SEAT1);
        assertThat(getCourseResponse.getCreatedAt()).isEqualTo(FormatConverter.parseToString(now));
        assertThat(getCourseResponse.getModifiedAt()).isEqualTo(FormatConverter.parseToString(now));
    }

    @DisplayName("강좌 양식을 전송해 새로운 강좌를 생성할 수 있다.")
    @Test
    void create() {
        // given
        CreateCourseRequest createCourseRequest = CourseTestObjectFactory.createCourseRequest(
                SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1, NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        dbConnection.when(DbConnection::initialize).thenAnswer(invocation -> null);
        TimePeriod timePeriod = mock(TimePeriod.class);
        when(timePeriodRepository.findById(createCourseRequest.getTimePeriodId())).thenReturn(timePeriod);
        when(timePeriod.getStartDate()).thenReturn(START_DATE1);
        when(timePeriod.getLastDate()).thenReturn(LAST_DATE1);

        // when
        courseService.create(createCourseRequest, timePeriodService);

        // then
        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository, times(1)).save(courseCaptor.capture());

        Course savedCourse = courseCaptor.getValue();

        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getSubjectId()).isEqualTo(SUBJECT_ID1);
        assertThat(savedCourse.getTeacherId()).isEqualTo(TEACHER_ID1);
        assertThat(savedCourse.getTimePeriodId()).isEqualTo(TIME_PERIOD_ID1);
        assertThat(savedCourse.getName()).isEqualTo(NAME1);
        assertThat(savedCourse.getDescription()).isEqualTo(DESCRIPTION1);
        assertThat(savedCourse.getPrice()).isEqualTo(PRICE1);
        assertThat(savedCourse.getStartDate()).isEqualTo(START_DATE1);
        assertThat(savedCourse.getLastDate()).isEqualTo(LAST_DATE1);
    }

    @DisplayName("강좌명 중복 여부를 검증하고, 중복되는 경우 true를 반환할 수 있다.")
    @Test
    void checkDuplicateCourseNameWhenTrue() {
        // given
        when(courseRepository.existsByName(anyString())).thenReturn(true);

        // when
        boolean isDuplicateName = courseService.checkDuplicateCourseName(NAME1);

        // then
        assertThat(isDuplicateName).isTrue();
    }

    @DisplayName("강좌명 중복 여부를 검증하고, 중복되지 않는 경우 false를 반환할 수 있다.")
    @Test
    void checkDuplicateCourseNameWhenFalse() {
        // given
        when(courseRepository.existsByName(anyString())).thenReturn(false);

        // when
        boolean isDuplicateName = courseService.checkDuplicateCourseName(NAME1);

        // then
        assertThat(isDuplicateName).isFalse();
    }
}
