package org.chulgang.hrd.classroom.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.classroom.dto.GetTimePeriodsResponse;
import org.chulgang.hrd.classroom.model.service.TimePeriodService;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.exception.JsonSerializationFailedException;
import org.chulgang.hrd.util.FormatConverter;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import static org.chulgang.hrd.course.util.RequestConstant.*;
import static org.chulgang.hrd.exception.ExceptionMessage.JSON_SERIALIZATION_FAILED_EXCEPTION_MESSAGE;

@WebServlet(urlPatterns = {GET_PERIODS_FIRST_REQUEST_URL, GET_PERIODS_SECOND_REQUEST_URL})
public class GetTimePeriodsController extends HttpServlet {
    private TimePeriodService timePeriodService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        timePeriodService = LoggingAspect.createProxy(TimePeriodService.class,
                (TimePeriodService) config.getServletContext().getAttribute(TIME_PERIOD_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains(String.format("%s/", CLASSROOM_DOMAIN_NAME))) {
            response.sendRedirect(GET_PERIODS_SECOND_REQUEST_URL);
            return;
        }

        String classroomId = request.getParameter(CLASSROOM_ID_PARAMETER_NAME);
        Long parsedClassroomId = FormatConverter.parseToLong(classroomId);

        String startDate = request.getParameter(START_DATE_PARAMETER_NAME);
        LocalDate parsedStartDate = FormatConverter.parseToDate(startDate);

        String lastDate = request.getParameter(LAST_DATE_PARAMETER_NAME);
        LocalDate parsedLastDate = FormatConverter.parseToDate(lastDate);

        GetTimePeriodsResponse getTimePeriodsResponse = timePeriodService.getUsableTimePeriodsByClassroomId(
                parsedClassroomId, parsedStartDate, parsedLastDate
        );

        StringBuilder timePeriodsJson = new StringBuilder();
        timePeriodsJson.append("[");

        for (int i = 0; i < getTimePeriodsResponse.size(); i++) {
            Long id = getTimePeriodsResponse.get(i).getId();
            String description = getTimePeriodsResponse.get(i).getPeriod().getDescription();
            boolean isUsed = getTimePeriodsResponse.get(i).isUsed();

            timePeriodsJson.append("{")
                    .append("\"id\":").append(id).append(",")
                    .append("\"description\":\"")
                    .append(description).append("\",")
                    .append("\"isUsed\":").append(isUsed)
                    .append("}");

            if (i < getTimePeriodsResponse.size() - 1) {
                timePeriodsJson.append(",");
            }
        }
        timePeriodsJson.append("]");

        try {
            response.setContentType(JSON_CONTENT_TYPE);
            PrintWriter pw = response.getWriter();
            pw.print(timePeriodsJson.toString());
            pw.flush();
        } catch (IOException ie) {
            GlobalExceptionHandler.throwCheckedException(
                    new JsonSerializationFailedException(
                            String.format(JSON_SERIALIZATION_FAILED_EXCEPTION_MESSAGE, timePeriodsJson.toString()))
            );
        }
    }
}
