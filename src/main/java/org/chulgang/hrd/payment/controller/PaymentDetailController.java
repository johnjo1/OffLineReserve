package org.chulgang.hrd.payment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.payment.dto.PaidCourseDetailResponse;
import org.chulgang.hrd.payment.model.service.PaymentService;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.PAYMENT_SERVICE_ATTRIBUTE_NAME;

@WebServlet(urlPatterns = {"/elearn/paid-course-detail.do"})
public class PaymentDetailController extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        paymentService = LoggingAspect.createProxy(PaymentService.class,
                (PaymentService) config.getServletContext().getAttribute(PAYMENT_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long payedCourseId = Long.parseLong(request.getParameter("payedCourseId"));
        PaidCourseDetailResponse paidCourseDetailResponse = paymentService.getPaidCourseDetail(payedCourseId);
        request.setAttribute("payedCourse", paidCourseDetailResponse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paid-course-detail.jsp");
        dispatcher.forward(request, response);
    }
}
