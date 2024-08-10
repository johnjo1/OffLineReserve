package org.chulgang.hrd.users.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.wallethistory.domain.WalletHistory;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryServiceImpl;

import java.io.IOException;

@WebServlet("/elearn/myPageForm.do")
public class MyPageFormController extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsersService service = UsersService.getInstance();
        WalletHistoryServiceImpl walletService =  WalletHistoryServiceImpl.getInstance();

        HttpSession session = request.getSession();
        UsersLoginResponse dto = (UsersLoginResponse)session.getAttribute("dto");
        System.out.println("dto::::" + dto.getEmail());
        long users_id = dto.getId();
        System.out.println("users_id::::   " + users_id);

        int current_amount = walletService.getCurrentAmountByUserId(users_id);
        System.out.println("current_amount::::   " + current_amount);
        request.setAttribute("dto",dto);
        request.setAttribute("current_amount",current_amount);
        RequestDispatcher rd = request.getRequestDispatcher("myPage.jsp");
        rd.forward(request, response);

    }
}
