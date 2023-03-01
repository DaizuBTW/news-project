package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import by.it.selvanovich.news.util.validator.IAccessValidation;
import by.it.selvanovich.news.util.validator.ValidatorProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToUpdateNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final IAccessValidation accessValidation = ValidatorProvider.getInstance().getAccessValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        HttpSession session = request.getSession();

        try {
            if (accessValidation.haveAdminPermissions(session)) {
                News news = newsService.findById(Integer.parseInt(id));
                request.setAttribute("news", news);
                request.setAttribute("presentation", "updateNews");
                request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
            } else {
                response.sendRedirect("controller?command=go_to_news_list");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("controller?command=go_to_error_page");
        }

    }

}
