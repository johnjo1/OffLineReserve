package org.chulgang.hrd.users.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.wallethistory.domain.WalletHistory;
import org.chulgang.hrd.wallethistory.model.repository.WalletHistoryRepositoryImpl;
import org.chulgang.hrd.wallethistory.model.service.WalletHistoryServiceImpl;

import java.io.IOException;


@WebServlet("/elearn/signUp.do")
public class SignUpController extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsersService service = UsersService.getInstance();
        WalletHistoryServiceImpl walletService =  WalletHistoryServiceImpl.getInstance();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String full_name = request.getParameter("full_name");
        String phone = request.getParameter("phone");
        String student = request.getParameter("student");
        String teacher = request.getParameter("teacher");

        //회원가입 진행 후 권한도 저장한다.
        long result = service.signUp(email,username,password,full_name,phone);
        WalletHistoryRepositoryImpl  walletRepository = WalletHistoryRepositoryImpl.getInstance();
        WalletHistory walletHistory = new WalletHistory();
        System .out.println("회원가입 리절트 유저아이디 " + result);
        walletHistory.setUserId(result);
        walletHistory.setAddedAmount(1);
        walletHistory.setRefundedAmount(1);
        walletHistory.setCurrentAmount(1);
        walletHistory.setUsedAmount(1);
        walletRepository.save(walletHistory);
        if(result != -1){
            if(student !=null && student != ""){
                service.insertUserRole(email,password,student);
                System .out.println("회원가입 student student " + student);

            }
            if(teacher !=null && teacher != ""){
                service.insertUserRole(email,password,teacher);
                System .out.println("회원가입 teacher teacher " + teacher);

            }
        }
        // 나중에 쓸 예정 PrintWriter pw = response.getWriter();
        //pw.println("<script>location.href='../index.jsp';</script>");
        response.sendRedirect("index.jsp");


    }
}
