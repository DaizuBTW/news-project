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

public class DoUpdateNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    private static final String JSP_TITLE_PARAM = "title";
    private static final String JSP_BRIEF_PARAM = "brief";
    private static final String JSP_CONTENT_PARAM = "content";
    private static final String JSP_DATE_PARAM = "date";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO добавить валидаторы

        String id;
        String title;
        String brief;
        String content;
        String date;

        id = request.getParameter("id");
        title = request.getParameter(JSP_TITLE_PARAM);
        brief = request.getParameter(JSP_BRIEF_PARAM);
        content = request.getParameter(JSP_CONTENT_PARAM);
        date = request.getParameter(JSP_DATE_PARAM);

        try {
            newsService.update(Integer.parseInt(id), title, brief, content, date);
            response.sendRedirect("controller?command=go_to_news_list");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("WEB-INF/pages/layouts/error.jsp");
        }

    }

}
