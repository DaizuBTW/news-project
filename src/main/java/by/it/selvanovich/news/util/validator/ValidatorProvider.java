package by.it.selvanovich.news.util.validator;


import by.it.selvanovich.news.util.validator.impl.SecurityAccess;

public class ValidatorProvider {

    private static final ValidatorProvider instance = new ValidatorProvider();
    private final ISecurityAccess securityAccess = new SecurityAccess();


    private ValidatorProvider() {
    }

    public ISecurityAccess getSecurityAccess() {
        return securityAccess;
    }

    public static ValidatorProvider getInstance() {
        return instance;
    }
}
