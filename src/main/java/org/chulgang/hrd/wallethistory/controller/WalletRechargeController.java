package org.chulgang.hrd.wallethistory.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.aop.LoggingAspect;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryService;

import java.io.IOException;

import static org.chulgang.hrd.course.util.RequestConstant.WALLET_HISTORY_SERVICE_ATTRIBUTE_NAME;

@WebServlet(urlPatterns = {"/elearn/wallet-recharge"})
public class WalletRechargeController extends HttpServlet {
    private WalletHistoryService walletHistoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        walletHistoryService = LoggingAspect.createProxy(WalletHistoryService.class,
                (WalletHistoryService) config.getServletContext().getAttribute(WALLET_HISTORY_SERVICE_ATTRIBUTE_NAME));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsersLoginResponse users = (UsersLoginResponse) session.getAttribute("dto");
        Long userId = users.getId();
        int amount = Integer.parseInt(request.getParameter("amount"));
        try {
            walletHistoryService.rechargeWallet(userId, amount);
            response.sendRedirect(request.getContextPath() + "myPageForm.do");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/404");
        }
    }
}
