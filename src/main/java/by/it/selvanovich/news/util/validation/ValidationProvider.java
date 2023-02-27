package by.it.selvanovich.news.util.validation;

import by.it.selvanovich.news.util.validation.impl.UserValidatorImpl;

public class ValidationProvider {

	private static final ValidationProvider instance = new ValidationProvider();

	private final UserValidator newsValidator = new UserValidatorImpl();

	private ValidationProvider() {
	}

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserValidator getNewsValidator() {
		return newsValidator;
	}
}