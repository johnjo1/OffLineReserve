package org.chulgang.hrd.users.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.wallethistory.domain.WalletHistory;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryServiceImpl;

import java.io.IOException;

@WebServlet("/elearn/chargeForm.do")
public class ChargeFormController extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WalletHistoryServiceImpl walletService =  WalletHistoryServiceImpl.getInstance();
        HttpSession session = request.getSession();

        UsersLoginResponse dto = (UsersLoginResponse)session.getAttribute("dto");
        long users_id = dto.getId();

        WalletHistory walletHistory =walletService.getLatestWalletHistoryByUserId(users_id);
        request.setAttribute("walletHistory",walletHistory);
        RequestDispatcher rd = request.getRequestDispatcher("charge.jsp");
        rd.forward(request, response);
    }
}
