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
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.FormatConverter;
import org.chulgang.hrd.util.FormatValidator;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.*;

@WebServlet(urlPatterns = {GET_COURSES_FIRST_REQUEST_URL, GET_COURSES_SECOND_REQUEST_URL})
public class GetCoursesController extends HttpServlet {
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
            response.sendRedirect(GET_COURSES_SECOND_REQUEST_URL);
            return;
        }

        if (FormatValidator.isNoValue(request.getParameter(SEARCH_WORD_PARAMETER_NAME))) {
            getAllCourses(request, response);
        }
    }

    private void getAllCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String size = request.getParameter(SIZE_PARAMETER_NAME);
        int parsedSize = 3;
        if (!FormatValidator.isNoValue(size)) {
            parsedSize = FormatConverter.parseToInt(size);
        }

        String pageNumber = request.getParameter(PAGE_NUMBER_PARAMETER_NAME);
        int parsedPageNumber = 1;
        if (!FormatValidator.isNoValue(pageNumber)) {
            parsedPageNumber = FormatConverter.parseToInt(pageNumber);
        }

        request.setAttribute(
                COURSES_ATTRIBUTE_NAME, courseService.getCourses(
                        parsedSize, parsedPageNumber, subjectService, usersService
                )
        );
        request.setAttribute(SIZE_PARAMETER_NAME, parsedSize);
        request.setAttribute(PAGE_COUNT_PARAMETER_NAME, courseService.getPageCount(parsedSize));
        request.setAttribute(PAGE_NUMBER_PARAMETER_NAME, parsedPageNumber);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(COURSES_VIEW);
        requestDispatcher.forward(request, response);
    }
}
