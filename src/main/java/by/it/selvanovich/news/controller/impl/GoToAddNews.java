package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.util.validator.IAccessValidation;
import by.it.selvanovich.news.util.validator.ValidatorProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToAddNews implements Command {

    private final IAccessValidation accessValidation = ValidatorProvider.getInstance().getAccessValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (accessValidation.haveAdminPermissions(session)) {
            request.setAttribute("presentation", "addNews");
            request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
        } else {
            response.sendRedirect("controller?command=go_to_news_list");
        }
    }
}
