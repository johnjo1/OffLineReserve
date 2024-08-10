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
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;


@WebServlet("/elearn/login.do")
public class LoginController extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsersService service = UsersService.getInstance();

        UsersLoginResponse dto = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String result = service.findByPassword(email,password);
        String result2 = service.findByEmail(email,password);

        int flag = -1;

        if(result == null && result2 == null){
            flag = -1;
            request.setAttribute("flag", flag);
            RequestDispatcher rd = request.getRequestDispatcher("../login.jsp");
            rd.forward(request, response);
        } else if(email.contains("@")){
            flag = 1;
            request.setAttribute("flag", flag);
            HttpSession session =request.getSession();
            dto = service.loginByEmail(email,password);
            System.out.println("로그인컨트롤 롤:: "+ dto.getRole());
            session.setAttribute("dto",dto);
            response.sendRedirect("index.jsp");
        }else if(!email.contains("@")){
            flag = 1;
            request.setAttribute("flag", flag);
            HttpSession session =request.getSession();
            dto= service.loginByUsername(email,password);
            System.out.println("로그인컨트롤 롤:: "+ dto.getRole());
            session.setAttribute("dto",dto);
            response.sendRedirect("index.jsp");
        }
    }
}
