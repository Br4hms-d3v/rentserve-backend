package be.brahms.rent_serve.exceptions.userFavor;

public class UserFavorNotFoundException extends RuntimeException {
    public UserFavorNotFoundException(String message) {
        super(message);
    }

    public UserFavorNotFoundException() {
        super("Le service n'a pas été retrouvé");
    }
}
