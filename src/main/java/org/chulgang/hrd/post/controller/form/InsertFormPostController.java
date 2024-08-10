package org.chulgang.hrd.post.controller.form;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

import java.io.IOException;

@WebServlet("/elearn/post_insertForm.do")
public class InsertFormPostController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");

        session.setAttribute("dto",user);
        request.setAttribute("Full_name",user.getFull_name());

        String view = "postInput.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
