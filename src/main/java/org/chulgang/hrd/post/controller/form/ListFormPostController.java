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
import java.util.ArrayList;

@WebServlet("/elearn/post_listForm.do")
public class ListFormPostController extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");
        PostService service = new PostServiceImpl();
        ArrayList<UsersLoginResponse> list_post = service.list_postsS();
        System.out.println("LIST_FORM"+list_post);

        session.setAttribute("list_post", list_post);

        String view = "postList.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
