package org.chulgang.hrd.users.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;

@WebServlet("/elearn/myPage.do")
public class MyPageController extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsersService service = UsersService.getInstance();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String full_name = request.getParameter("full_name");
        String phone = request.getParameter("phone");
        String get_username = service.findByPassword(username,password);
        int flag = -1;

        if(get_username== "" || get_username == null){
            request.setAttribute("flag", flag);
            RequestDispatcher rd = request.getRequestDispatcher("myPageForm.do");
            rd.forward(request, response);
        }
        if(get_username.equals(username)){
            int result = service.modifyMyPage(email,username,password,full_name,phone);
            //PrintWriter pw = response.getWriter();
            // pw.println("<script>location.href='../index.jsp';</script>");
            response.sendRedirect("index.jsp");
        }



        //response.sendRedirect("../index.jsp");
        //RequestDispatcher rd = request.getRequestDispatcher("myPage.jsp");
        //rd.forward(request, response);

    }





           


}
