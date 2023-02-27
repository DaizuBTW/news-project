package by.it.selvanovich.news.util.validation;

import jakarta.servlet.http.HttpSession;

public interface UserValidator {
    boolean haveSession(HttpSession session) throws UserValidatorExeption;
    boolean isAdmin(String role) throws UserValidatorExeption;
}
