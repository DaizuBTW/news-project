package by.it.selvanovich.news.util.validator;

import by.it.selvanovich.news.bean.User;

public interface IUserValidator {
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean isNameValid(String name);
    boolean isSurnameValid(String surname);
    boolean isUserValid(User user);
}
