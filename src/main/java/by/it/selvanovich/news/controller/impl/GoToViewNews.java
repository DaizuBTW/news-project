package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToViewNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News news;
        String id;

        id = request.getParameter("id");

        try {
            news = newsService.findById(Integer.parseInt(id));
            request.setAttribute("news", news);
            request.setAttribute("presentation", "viewNews");
            request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("controller?command=go_to_error_page");
        }

    }

}
