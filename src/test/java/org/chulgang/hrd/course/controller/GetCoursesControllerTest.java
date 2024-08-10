package org.chulgang.hrd.course.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.dto.GetCoursesResponse;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.SubjectService;
import org.chulgang.hrd.course.model.service.SubjectServiceImpl;
import org.chulgang.hrd.course.model.testutil.CourseTestObjectFactory;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.chulgang.hrd.course.model.testutil.CourseTestConstant.*;
import static org.chulgang.hrd.course.util.RequestConstant.*;
import static org.mockito.Mockito.*;

public class GetCoursesControllerTest {
    private CourseService courseService;
    SubjectService subjectService;
    UsersService usersService;

    private GetCoursesController getCoursesController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private ServletConfig servletConfig;
    private ServletContext servletContext;

    @BeforeEach
    void setUp() throws ServletException {
        courseService = mock(CourseService.class);
        subjectService = mock(SubjectService.class);
        usersService = mock(UsersService.class);

        getCoursesController = new GetCoursesController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        servletConfig = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(COURSE_SERVICE_ATTRIBUTE_NAME)).thenReturn(courseService);
        when(servletContext.getAttribute(SUBJECT_SERVICE_ATTRIBUTE_NAME)).thenReturn(subjectService);
        when(servletContext.getAttribute(USER_SERVICE_ATTRIBUTE_NAME)).thenReturn(usersService);

        getCoursesController.init(servletConfig);
    }

    @DisplayName("전체 강좌 목록 조회 요청을 처리할 수 있다.")
    @Test
    void doGetAllCourses() throws ServletException, IOException {
        // Given
        when(request.getRequestURI()).thenReturn(GET_COURSES_SECOND_REQUEST_URL);
        when(request.getParameter(SIZE_PARAMETER_NAME)).thenReturn(String.valueOf(SIZE1));
        when(request.getParameter(PAGE_NUMBER_PARAMETER_NAME)).thenReturn(String.valueOf(PAGE_NUMBER));
        when(request.getParameter(SEARCH_WORD_PARAMETER_NAME)).thenReturn(null);
        when(request.getRequestDispatcher(COURSES_VIEW)).thenReturn(requestDispatcher);

        Course course1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        Course course2 = CourseTestObjectFactory.createCourse(
                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
        );
        GetCoursesResponse getCoursesResponse = GetCoursesResponse.from(List.of(course1, course2), PAGE_COUNT);

        when(courseService.getCourses(SIZE1, PAGE_NUMBER, SubjectServiceImpl.getInstance(), UsersService.getInstance())).thenReturn(getCoursesResponse);
        when(courseService.getPageCount(SIZE1)).thenReturn(PAGE_COUNT);

        // When
        getCoursesController.doGet(request, response);

        // Then
        ArgumentCaptor<String> attributeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        verify(request, times(4)).setAttribute(attributeCaptor.capture(), valueCaptor.capture());

        List<String> capturedAttributes = attributeCaptor.getAllValues();
        List<Object> capturedValues = valueCaptor.getAllValues();

        assertThat(capturedAttributes.get(0)).isEqualTo(COURSES_ATTRIBUTE_NAME);
        assertThat(capturedAttributes.get(1)).isEqualTo(SIZE_PARAMETER_NAME);
        assertThat(capturedValues.get(1)).isEqualTo(SIZE1);
        assertThat(capturedAttributes.get(2)).isEqualTo(PAGE_COUNT_PARAMETER_NAME);
        assertThat(capturedValues.get(2)).isEqualTo(PAGE_COUNT);
        assertThat(capturedAttributes.get(3)).isEqualTo(PAGE_NUMBER_PARAMETER_NAME);
        assertThat(capturedValues.get(3)).isEqualTo(PAGE_NUMBER);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    @DisplayName("상위 경로의 요청으로 리다이렉트할 수 있다.")
    void doGetWithRedirect() throws ServletException, IOException {
        // Given
        when(request.getRequestURI()).thenReturn(GET_COURSES_FIRST_REQUEST_URL);

        // When
        getCoursesController.doGet(request, response);

        // Then
        verify(response).sendRedirect(GET_COURSES_SECOND_REQUEST_URL);
    }
}
