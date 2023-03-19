package by.it.selvanovich.news.controller.impl;

import by.it.selvanovich.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToErrorPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO доработать вывод ошибок
        String message = (String) request.getSession().getAttribute("errorMessage");
        if (message != null) {
            System.out.println("123" + message);
            request.getSession(true).setAttribute("error", message + "852");
        }
        request.setAttribute("presentation", "error");
        request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);

    }

}
