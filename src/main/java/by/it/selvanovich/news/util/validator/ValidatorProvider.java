package by.it.selvanovich.news.util.validator;


import by.it.selvanovich.news.util.validator.impl.AccessValidation;

public class ValidatorProvider {

    private static final ValidatorProvider instance = new ValidatorProvider();
    private final IAccessValidation accessValidation = new AccessValidation();


    private ValidatorProvider() {
    }

    public IAccessValidation getAccessValidation() {
        return accessValidation;
    }

    public static ValidatorProvider getInstance() {
        return instance;
    }
}
