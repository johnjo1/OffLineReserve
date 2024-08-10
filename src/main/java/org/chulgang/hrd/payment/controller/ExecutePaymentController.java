package org.chulgang.hrd.payment.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.payment.dto.ExecutePaymentRequest;
import org.chulgang.hrd.payment.model.service.PaymentService;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.PAYMENT_SERVICE_ATTRIBUTE_NAME;

@WebServlet(urlPatterns = {"/elearn/execute-payment.do"})
public class ExecutePaymentController extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        paymentService = LoggingAspect.createProxy(PaymentService.class,
                (PaymentService) config.getServletContext().getAttribute(PAYMENT_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isPaymentSuccessful = paymentService.executePayment(ExecutePaymentRequest.from(request)) ;

        if (isPaymentSuccessful) {
            response.sendRedirect(request.getContextPath() + "/elearn/payment-list.do");
        } else {
            response.sendRedirect(request.getContextPath() + "/404");
        }
    }
}
