package org.chulgang.hrd.filtration;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    private List<String> excludedUrls;
    private int count;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = ((HttpServletRequest) req).getRequestURI();

        if (path.contains("login.do")||path.contains("signUpForm.do")||path.contains("/elearn/index.jsp")||path.contains("loginForm.do")) {

        } else {
            if(session != null || session.getAttribute("dto")==null){
                System.out.println("요한이 만든 필터 적용됨.");
                if (count++ < 1) {
                    res.sendRedirect("/elearn/index.jsp");
                    return;
                }
            }
        }
            chain.doFilter(request, response);
    }
}
