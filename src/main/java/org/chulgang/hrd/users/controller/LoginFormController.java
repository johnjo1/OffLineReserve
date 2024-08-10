package org.chulgang.hrd.users.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/elearn/loginForm.do")
public class LoginFormController extends HttpServlet{

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("login.jsp");





    }
}
