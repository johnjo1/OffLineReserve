package org.chulgang.hrd.course.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.classroom.dto.GetClassroomsResponse;
import org.chulgang.hrd.classroom.model.service.ClassroomService;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.course.dto.CreateCourseRequest;
import org.chulgang.hrd.course.dto.GetSubjectsResponse;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.SubjectService;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.exception.JsonSerializationFailedException;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static org.chulgang.hrd.course.util.RequestConstant.*;
import static org.chulgang.hrd.exception.ExceptionMessage.JSON_SERIALIZATION_FAILED_EXCEPTION_MESSAGE;


@WebServlet(urlPatterns = {REGISTER_COURSE_FIRST_REQUEST_URL, REGISTER_COURSE_SECOND_REQUEST_URL, VALIDATION_URL})
public class RegisterCourseController extends HttpServlet {
    private CourseService courseService;
    private SubjectService subjectService;
    private ClassroomService classroomService;
    private TimePeriodService timePeriodService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        courseService = LoggingAspect.createProxy(CourseService.class,
                (CourseService) config.getServletContext().getAttribute(COURSE_SERVICE_ATTRIBUTE_NAME));

        subjectService = LoggingAspect.createProxy(SubjectService.class,
                (SubjectService) config.getServletContext().getAttribute(SUBJECT_SERVICE_ATTRIBUTE_NAME));

        classroomService = LoggingAspect.createProxy(ClassroomService.class,
                (ClassroomService) config.getServletContext().getAttribute(CLASSROOM_SERVICE_ATTRIBUTE_NAME));

        timePeriodService = LoggingAspect.createProxy(TimePeriodService.class,
                (TimePeriodService) config.getServletContext().getAttribute(TIME_PERIOD_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains(String.format("%s/", COURSE_DOMAIN_NAME))) {
            response.sendRedirect(REGISTER_COURSE_SECOND_REQUEST_URL);
            return;
        }

        HttpSession httpSession = request.getSession();
        UsersLoginResponse usersLoginResponse
                = (UsersLoginResponse) httpSession.getAttribute(LOGIN_SESSION_ATTRIBUTE_NAME);

        if (usersLoginResponse == null) {
            response.sendRedirect(LOGIN_FAILED_VIEW);
            return;
        }
        if (!usersLoginResponse.getRole().equals(TEACHER_ROLE_NAME)) {
            response.sendRedirect(AUTHORIZATION_FAILED_VIEW);
            return;
        }

        GetSubjectsResponse getSubjectsResponse = subjectService.getSubjects();
        GetClassroomsResponse getClassroomsResponse = classroomService.getClassrooms();

        request.setAttribute(GET_SUBJECTS_ATTRIBUTE_NAME, getSubjectsResponse);
        request.setAttribute(GET_CLASSROOMS_ATTRIBUTE_NAME, getClassroomsResponse);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(COURSE_REGISTRATION_VIEW);
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals(VALIDATION_URL)) {
            validateDuplicateCourseName(request, response);
            return;
        }

        boolean isSuccess = courseService.create(CreateCourseRequest.from(request), timePeriodService);
        request.setAttribute("isSuccess", isSuccess);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(COURSE_REGISTRATION_CONFIRM_VIEW);
        requestDispatcher.forward(request, response);
    }

    private void validateDuplicateCourseName(HttpServletRequest request, HttpServletResponse response) {
        boolean isDuplicateName
                = courseService.checkDuplicateCourseName(request.getParameter(COURSE_NAME_PARAMETER_NAME));
        StringBuilder duplicateJson = new StringBuilder("{\"isDuplicateName\": \"");
        duplicateJson.append(isDuplicateName);
        duplicateJson.append("\"}");

        try {
            response.setContentType(JSON_CONTENT_TYPE);
            PrintWriter pw = response.getWriter();
            pw.print(duplicateJson.toString());
            pw.flush();
        } catch (IOException ie) {
            GlobalExceptionHandler.throwCheckedException(
                    new JsonSerializationFailedException(
                            String.format(JSON_SERIALIZATION_FAILED_EXCEPTION_MESSAGE, duplicateJson.toString()))
            );
        }
    }
}
