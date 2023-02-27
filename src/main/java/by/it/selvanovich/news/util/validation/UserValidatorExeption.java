package by.it.selvanovich.news.util.validation;

public class UserValidatorExeption extends Exception {

    private static final long serialVersionUID = 1L;

    public UserValidatorExeption() {
        super();
    }

    public UserValidatorExeption(String message) {
        super(message);
    }

    public UserValidatorExeption(Exception e) {
        super(e);
    }

    public UserValidatorExeption(String message, Exception e) {
        super(message, e);
    }
}
