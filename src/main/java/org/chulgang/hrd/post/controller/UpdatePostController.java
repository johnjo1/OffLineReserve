package org.chulgang.hrd.post.controller;

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
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;

@WebServlet("/elearn/post_update.do")
public class UpdatePostController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        PostService service = new PostServiceImpl();

        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        Post Session_post = (Post) session.getAttribute("post");
        System.out.println("Controller subject: " + subject);
        System.out.println("Controller content: " + content);

        Post post = new Post(Session_post.getId(), Session_post.getWriter_id(),subject, content, 1);

        System.out.println("UpdatePost Controller: "+post.getId());

        service.update_PostS(post);

        response.sendRedirect("post_listForm.do");
    }
}
