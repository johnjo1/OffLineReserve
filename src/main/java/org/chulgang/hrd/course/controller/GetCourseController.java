package org.chulgang.hrd.course.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.course.model.service.CourseService;
import org.chulgang.hrd.course.model.service.SubjectService;
import org.chulgang.hrd.exception.ArgumentNoValueException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.FormatConverter;
import org.chulgang.hrd.util.FormatValidator;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.*;
import static org.chulgang.hrd.exception.ExceptionMessage.ARGUMENT_NO_VALUE_EXCEPTION_MESSAGE;

@WebServlet(urlPatterns = {GET_COURSE_FIRST_REQUEST_URL, GET_COURSE_SECOND_REQUEST_URL})
public class GetCourseController extends HttpServlet {
    private CourseService courseService;
    private SubjectService subjectService;
    private UsersService usersService = UsersService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        courseService = LoggingAspect.createProxy(CourseService.class,
                (CourseService) config.getServletContext().getAttribute(COURSE_SERVICE_ATTRIBUTE_NAME));

        subjectService = LoggingAspect.createProxy(SubjectService.class,
                (SubjectService) config.getServletContext().getAttribute(SUBJECT_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains(String.format("%s/", COURSE_DOMAIN_NAME))) {
            response.sendRedirect(
                    String.format(
                            "%s?%s=%s",
                            GET_COURSE_SECOND_REQUEST_URL, ID_PARAMETER_NAME, request.getParameter(ID_PARAMETER_NAME)
                    )
            );
            return;
        }

        String id = request.getParameter(ID_PARAMETER_NAME);
        if (FormatValidator.isNoValue(id)) {
            GlobalExceptionHandler.throwRuntimeException(
                    new ArgumentNoValueException(ARGUMENT_NO_VALUE_EXCEPTION_MESSAGE)
            );
            return;
        }
        Long parsedId = FormatConverter.parseToLong(id);

        request.setAttribute(COURSE_DOMAIN_NAME, courseService.getCourse(parsedId, subjectService, usersService));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(COURSE_DETAIL_VIEW);
        requestDispatcher.forward(request, response);
    }
}
