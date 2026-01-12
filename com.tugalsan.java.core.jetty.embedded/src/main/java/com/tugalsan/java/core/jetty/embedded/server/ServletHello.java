package com.tugalsan.java.core.jetty.embedded.server;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class ServletHello extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello world!</h1>");
    }
}
