package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.IUserService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoRegistration implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    private static final String JSP_USERNAME_PARAM = "username";
    private static final String JSP_PASSWORD_PARAM = "password";
    private static final String JSP_NAME_PARAM = "name";
    private static final String JSP_SURNAME_PARAM = "surname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter(JSP_USERNAME_PARAM);
        String password = request.getParameter(JSP_PASSWORD_PARAM);
        String name = request.getParameter(JSP_NAME_PARAM);
        String surname = request.getParameter(JSP_SURNAME_PARAM);

        try {
            if (service.registration(new User(username, password, name, surname, "user"))) {
                request.getSession().setAttribute("errorMessage", "message");
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                request.setAttribute("error", "local.error.name.reg_error");
                request.getRequestDispatcher("controller?command=go_to_news_list").forward(request, response);
                //response.sendRedirect("controller?command=go_to_base_page#registration");
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute("errorMessage", "message");
            e.printStackTrace();
            response.sendRedirect("controller?command=go_to_error_page");
        }

    }

}
