package org.chulgang.hrd.post.controller;

import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.post.domain.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.chulgang.hrd.post.model.service.PostService;
import org.chulgang.hrd.post.model.service.PostServiceImpl;
import org.chulgang.hrd.users.dto.UsersLoginResponse;
import org.chulgang.hrd.util.DbConnection;

import java.io.IOException;

@WebServlet("/elearn/post_insert.do")
public class InsertPostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");
        PostService service = new PostServiceImpl();

        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        System.out.println("Controller subject: " + subject);
        System.out.println("Controller content: " + content);

        String full_name = (String) session.getAttribute("full_name");

        System.out.println("Controller getID: " + user.getId());
        Post post = new Post(user.getId(), subject, content, 1, full_name);
        service.insert_PostS(post);

        response.sendRedirect("post_listForm.do");

//        String view = "postInput.jsp";
//        RequestDispatcher rd = request.getRequestDispatcher(view);
//        rd.forward(request, response);

//        PostService service = new PostServiceImpl();
//        service.insertPostS(post, usersId);



}
    }
