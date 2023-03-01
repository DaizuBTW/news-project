package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoSignOut implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(true).setAttribute("user", "not active");
        request.getSession().setAttribute("role", null);
        response.sendRedirect("index.jsp");

    }

}
