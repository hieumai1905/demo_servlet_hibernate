package com.example.servlethibernate;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@WebServlet(name = "User", value = "/users/*")
public class HelloServlet extends HttpServlet {
    private String message;
    private UserDAO userDAO;

    public void init() {
        // lay  SessionFactory tu ServletContext
        SessionFactory session = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        message = "Hello World!";
        userDAO = new UserDAO(session);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = userDAO.findById(1);

        out.println("<html><body>");

        out.println("<h1>Name: " + user.getName() + "</h1>");
        out.println("<h1>Address: " + user.getAddress() + "</h1>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}