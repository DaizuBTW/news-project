package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.IUserService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoSIgnIn implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    private static final String JSP_USERNAME_PARAM = "username";
    private static final String JSP_PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO добавить валидаторы

        String username;
        String password;

        username = request.getParameter(JSP_USERNAME_PARAM);
        password = request.getParameter(JSP_PASSWORD_PARAM);

        try {

            String role = service.authorization(username, password);

            if (!role.equals("guest")) {
                request.getSession(true).setAttribute("user", "active");
                request.getSession().setAttribute("role", role);
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                request.getSession(true).setAttribute("user", "not active");
                request.setAttribute("AuthenticationError", "wrong login or password");
                request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("WEB-INF/pages/layouts/error.jsp");
        }
    }

}
