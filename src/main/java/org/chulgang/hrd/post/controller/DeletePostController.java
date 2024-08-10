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
import java.util.ArrayList;

@WebServlet("/elearn/post_delete.do")

public class DeletePostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                DbConnection.reset();
                
                HttpSession session = request.getSession();
                String strpost_id = request.getParameter("post_id");
                System.out.println("strpostid널널::::  " + strpost_id);

                long post_id = 0L;
                if(strpost_id != null && strpost_id != ""){
                    System.out.println("노 널::::  " + strpost_id);
                    post_id = Long.parseLong(strpost_id);
                }
                //UsersLoginResponse user = (UsersLoginResponse) session.getAttribute("dto");
                //삭제 메소드 실행
               // long user_id  = user.getId();

                PostService service = new PostServiceImpl();
                service. delete_PostS(post_id);
                response.sendRedirect("post_listForm.do");

    }
}
