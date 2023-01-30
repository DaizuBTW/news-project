package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoLocalization implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("local", request.getParameter("local"));
        response.sendRedirect("controller?command=go_to_news_list");
    }
}
