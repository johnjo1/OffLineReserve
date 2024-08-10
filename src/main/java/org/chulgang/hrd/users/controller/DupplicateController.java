package org.chulgang.hrd.users.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chulgang.hrd.users.model.usersService.UsersService;
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/elearn/dupplicate.do")
public class DupplicateController extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UsersService service = UsersService.getInstance();
        String username = request.getParameter("username");
        int get_username_result = service.dupplicate(username);
        String  html ="";

        if(get_username_result == 1 ){
            html +=
                    "{\"username\":"+get_username_result+"}";
            PrintWriter pw = response.getWriter();
            pw.print(html);
          //  pw.println("<script>alert('아이디가 있습니다');</script>");
        }else if(get_username_result == -1){
            html +=
                    "{\"username\":"+get_username_result+"}";
            PrintWriter pw = response.getWriter();
            pw.print(html);
        }
    }
}
