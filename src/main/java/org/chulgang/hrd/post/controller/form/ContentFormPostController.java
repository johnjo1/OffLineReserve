package org.chulgang.hrd.post.controller.form;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.chulgang.hrd.post.domain.Post;
import org.chulgang.hrd.users.dto.UsersLoginResponse;

import java.io.IOException;

@WebServlet("/elearn/post_contentForm.do")
public class ContentFormPostController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");

        long post_id = Long.parseLong(request.getParameter("post_id"));
        long post_writer_id = Long.parseLong(request.getParameter("post_writer_id"));
        String post_content = request.getParameter("post_content");
        String post_subject = request.getParameter("post_subject");

        Post post = new Post(post_id, post_writer_id, post_subject, post_content,
                0, null, null);


        String refull_name= request.getParameter("refull_name");

        session.setAttribute("post", post);

        System.out.println("@@@@@@@@@@@@@@@@@@@"+refull_name);
        if(user == null) { //로그인 안했을 때
            String view = "postContent.jsp";
            request.setAttribute("user",user);
            request.setAttribute("refull_name", refull_name);
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        } else {
            session = request.getSession();
            user = (UsersLoginResponse) session.getAttribute("dto");
            request.setAttribute("user",user);
            String Session_full_name = (String) session.getAttribute("Session_full_name");
            System.out.println("CONTROLEL"+user.getRole());
            request.setAttribute("user_role", user.getRole());
            request.setAttribute("refull_name", refull_name);
            request.setAttribute("Session_full_name", Session_full_name);
            System.out.println("ConteFoPostCon refull_name++"+ refull_name);
            System.out.println("ConteFoPostCon Session_full_name++"+Session_full_name);

            post_id = Long.parseLong(request.getParameter("post_id"));
            post_writer_id = Long.parseLong(request.getParameter("post_writer_id"));
            post_content = request.getParameter("post_content");
            post_subject = request.getParameter("post_subject");

            post = new Post(post_id, post_writer_id, post_subject, post_content,
                    0, null, null);


            session.setAttribute("post", post);
            System.out.println("ContentForm user id" + user.getId());
            System.out.println("ContentForm post id" + post.getWriter_id());

            String view = "postContent.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        }
    }
}
