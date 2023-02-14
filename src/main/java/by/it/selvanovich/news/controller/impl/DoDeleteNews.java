package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoDeleteNews implements Command {
    // TODO добавить валидаторы

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] id;
        id = request.getParameterValues("id");

        try {
            newsService.delete(id);
            response.sendRedirect("controller?command=go_to_news_list");
        } catch (ServiceException e) {
            e.printStackTrace();
            request.getRequestDispatcher("WEB-INF/pages/layouts/error.jsp").forward(request, response);
        }

    }

}
