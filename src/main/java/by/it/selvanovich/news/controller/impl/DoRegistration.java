package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.IUserService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoRegistration implements Command {
    // TODO добавить валидаторы

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    private static final String JSP_USERNAME_PARAM = "username";
    private static final String JSP_PASSWORD_PARAM = "password";
    private static final String JSP_NAME_PARAM = "name";
    private static final String JSP_SURNAME_PARAM = "surname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username;
        String password;
        String name;
        String surname;

        username = request.getParameter(JSP_USERNAME_PARAM);
        password = request.getParameter(JSP_PASSWORD_PARAM);
        name = request.getParameter(JSP_NAME_PARAM);
        surname = request.getParameter(JSP_SURNAME_PARAM);

        try {
            service.registration(username, password, name, surname);
            response.sendRedirect("controller?command=go_to_base_page");
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendRedirect("WEB-INF/pages/layouts/error.jsp");
        }

    }

}
