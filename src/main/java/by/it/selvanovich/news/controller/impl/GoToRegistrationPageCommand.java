package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import by.it.selvanovich.news.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(request, response);
    }

}
