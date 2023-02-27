package by.it.selvanovich.news.util.validation.impl;

import by.it.selvanovich.news.util.validation.UserValidator;
import by.it.selvanovich.news.util.validation.UserValidatorExeption;
import jakarta.servlet.http.HttpSession;

public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean haveSession(HttpSession session) throws UserValidatorExeption {
        return false;
    }

    @Override
    public boolean isAdmin(String role) throws UserValidatorExeption {
        return false;
    }
}
