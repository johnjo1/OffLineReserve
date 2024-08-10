package org.chulgang.hrd.payment.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.payment.dto.PaymentCardResponse;
import org.chulgang.hrd.payment.model.service.PaymentService;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;
import java.util.List;

import static org.chulgang.hrd.course.util.RequestConstant.PAYMENT_SERVICE_ATTRIBUTE_NAME;

@WebServlet(urlPatterns = {"/elearn/payment-list.do"})
public class PaymentsController extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        paymentService = LoggingAspect.createProxy(PaymentService.class,
                (PaymentService) config.getServletContext().getAttribute(PAYMENT_SERVICE_ATTRIBUTE_NAME));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");
        Long userId = user.getId();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<PaymentCardResponse> payments = paymentService.getPagedPayments(userId, page);
        int totalPages = paymentService.getTotalPayments(userId);

        request.setAttribute("payments", payments);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("paid-course-list.jsp");
        dispatcher.forward(request, response);
    }
}
