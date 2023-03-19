package by.it.selvanovich.news.util.validator;


import by.it.selvanovich.news.util.validator.impl.NewsValidator;
import by.it.selvanovich.news.util.validator.impl.SecurityAccess;
import by.it.selvanovich.news.util.validator.impl.UserValidator;

public class ValidatorProvider {

    private static final ValidatorProvider instance = new ValidatorProvider();
    private final ISecurityAccess securityAccess = new SecurityAccess();
    private final IUserValidator userValidator = new UserValidator();
    private final INewsValidator newsValidator = new NewsValidator();


    private ValidatorProvider() {
    }

    public ISecurityAccess getSecurityAccess() {
        return securityAccess;
    }
    public IUserValidator getUserValidator() {
        return userValidator;
    }
    public INewsValidator getNewsValidator() {
        return newsValidator;
    }

    public static ValidatorProvider getInstance() {
        return instance;
    }
}
