package org.chulgang.hrd.reservation.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.reservation.dto.ReservationCourseDetailResponse;
import org.chulgang.hrd.reservation.model.service.ReservationService;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.RESERVATION_SERVICE_ATTRIBUTE_NAME;

@WebServlet(urlPatterns = {"/elearn/reservation-detail.do"})
public class ReservationDetailController extends HttpServlet {
    private ReservationService reservationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reservationService = LoggingAspect.createProxy(ReservationService.class,
                (ReservationService) config.getServletContext().getAttribute(RESERVATION_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long reservationCourseId = Long.parseLong(request.getParameter("reservationCourseId"));
        ReservationCourseDetailResponse reservationCourseDetailResponse = reservationService.getReservationCourseDetail(reservationCourseId);
        request.setAttribute("reservationCourse", reservationCourseDetailResponse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservation-detail.jsp");
        dispatcher.forward(request, response);
    }
}
