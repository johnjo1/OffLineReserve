package org.chulgang.hrd.post.controller.form;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.post.domain.Post;
import org.chulgang.hrd.post.model.service.PostService;
import org.chulgang.hrd.post.model.service.PostServiceImpl;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

import java.io.IOException;

@WebServlet("/elearn/post_updateForm.do")
public class UpdateFormPostController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =  request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");

        String refull_name= request.getParameter("refull_name");
        String Session_full_name = (String) session.getAttribute("Session_full_name");
        request.setAttribute("refull_name", refull_name);
        request.setAttribute("Session_full_name", Session_full_name);
        Post post = (Post) session.getAttribute("post");

        session.setAttribute("post", post);
        System.out.println("post: "+ post.getId());

        request.setAttribute(".",user.getFull_name());

        String view = "postUpdate.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
