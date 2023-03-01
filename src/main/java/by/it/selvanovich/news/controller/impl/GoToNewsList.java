package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import by.it.selvanovich.news.util.validator.ISecurityAccess;
import by.it.selvanovich.news.util.validator.ValidatorProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class GoToNewsList implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final ISecurityAccess accessValidation = ValidatorProvider.getInstance().getSecurityAccess();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<News> newsList;

        HttpSession session = request.getSession();

        try {
            if (accessValidation.haveAuthorizedUser(session)) {
                newsList = newsService.getList();
                request.setAttribute("presentation", "newsList");
            } else {
                newsList = newsService.latestList(5);
            }
            request.setAttribute("news", newsList);
            request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("controller?command=go_to_error_page");
        }

    }

}
