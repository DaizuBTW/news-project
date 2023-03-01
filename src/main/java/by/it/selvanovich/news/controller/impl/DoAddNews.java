package by.it.selvanovich.news.controller.impl;

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

public class DoAddNews implements Command {

    private final INewsService service = ServiceProvider.getInstance().getNewsService();
    private final ISecurityAccess accessValidation = ValidatorProvider.getInstance().getSecurityAccess();


    private static final String JSP_TITLE_PARAM = "title";
    private static final String JSP_DATE_PARAM = "date";
    private static final String JSP_BRIEF_PARAM = "brief";
    private static final String JSP_CONTENT_PARAM = "content";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter(JSP_TITLE_PARAM);
        String date = request.getParameter(JSP_DATE_PARAM);
        String brief = request.getParameter(JSP_BRIEF_PARAM);
        String content = request.getParameter(JSP_CONTENT_PARAM);

        HttpSession session = request.getSession();

        try {
            if (accessValidation.haveAdminPermissions(session)) {
                service.addNews(title, brief, content, date);
            } else {
                // TODO вывод сообщения с ошибкой
            }
            response.sendRedirect("controller?command=go_to_news_list");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("controller?command=go_to_error_page");
        }

    }

}
