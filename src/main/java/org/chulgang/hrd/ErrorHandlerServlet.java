package org.chulgang.hrd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/elearn/error")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String message = (String) request.getAttribute("jakarta.servlet.error.message");

        response.setContentType("application/json");
        response.setStatus(statusCode != null ? statusCode : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        PrintWriter writer = response.getWriter();
        writer.write("{");
        writer.write("\"title\": \"An error occurred\",");
        writer.write("\"message\": \"" + (message != null ? message : "Unknown error") + "\"");
        writer.write("}");
        writer.flush();
    }
}
