package org.chulgang.hrd.course.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.course.dto.GetCourseResponse;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.SubjectService;
import org.chulgang.hrd.course.model.testutil.CourseTestObjectFactory;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.chulgang.hrd.course.model.testutil.CourseTestConstant.*;
import static org.chulgang.hrd.course.util.RequestConstant.*;
import static org.mockito.Mockito.*;

public class GetCourseControllerTest {
    CourseService courseService;
    SubjectService subjectService;
    UsersService usersService;

    GetCourseController getCourseController = new GetCourseController();
    private ServletConfig servletConfig;
    private ServletContext servletContext;

    @BeforeEach
    void setUp() {
        courseService = mock(CourseService.class);
        subjectService = mock(SubjectService.class);
        usersService = mock(UsersService.class);
        servletConfig = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
    }

    @DisplayName("강좌 ID를 통해 해당 강좌 조회를 요청하고 처리할 수 있다.")
    @Test
    void doGet() throws ServletException, IOException {
        // Given
        LocalDateTime now = LocalDateTime.now();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(COURSE_SERVICE_ATTRIBUTE_NAME)).thenReturn(courseService);
        when(servletContext.getAttribute(SUBJECT_SERVICE_ATTRIBUTE_NAME)).thenReturn(subjectService);
        when(servletContext.getAttribute(USER_SERVICE_ATTRIBUTE_NAME)).thenReturn(usersService);

        getCourseController.init(servletConfig);

        when(request.getRequestURI()).thenReturn(GET_COURSE_SECOND_REQUEST_URL);
        when(request.getParameter(ID_PARAMETER_NAME)).thenReturn(String.valueOf(COURSE_ID1));
        when(request.getRequestDispatcher(COURSE_DETAIL_VIEW)).thenReturn(requestDispatcher);

        GetCourseResponse getCourseResponse = CourseTestObjectFactory.createCourseResponse(
                COURSE_ID1, SUBJECT_ID1, SUBJECT_NAME, TEACHER_ID1, TEACHER_NAME, TIME_PERIOD_ID1, NAME1,
                DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1, AVERAGE_SCORE1, REMAINED_SEAT1, now, now
        );
        when(courseService.getCourse(eq(SUBJECT_ID1), any(SubjectService.class), any(UsersService.class)))
                .thenReturn(getCourseResponse);
        when(subjectService.getSubjectName(SUBJECT_ID1)).thenReturn(SUBJECT_NAME);
        when(usersService.findById(TEACHER_ID1)).thenReturn(TEACHER_NAME);

        // When
        getCourseController.doGet(request, response);

        // Then
        ArgumentCaptor<String> attributeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        verify(request, times(1)).setAttribute(attributeCaptor.capture(), valueCaptor.capture());
        System.out.println("Captured Attribute Key: " + attributeCaptor.getValue());
        System.out.println("Captured Attribute Value: " + valueCaptor.getValue());
        assertThat(attributeCaptor.getValue()).isEqualTo(COURSE_DOMAIN_NAME);
        assertThat(valueCaptor.getValue()).isEqualTo(getCourseResponse);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    @DisplayName("강좌 조회 요청 후 상위 경로의 요청으로 리다이렉트할 수 있다.")
    void doGetWithRedirect() throws ServletException, IOException {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getRequestURI()).thenReturn("/elearn/course/course-details.do");
        when(request.getParameter("id")).thenReturn("1");

        // When
        getCourseController.doGet(request, response);

        // Then
        verify(response).sendRedirect("/elearn/course-details.do?id=1");
    }
}
