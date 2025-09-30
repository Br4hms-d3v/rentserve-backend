package be.brahms.rent_serve.exceptions.userFavor;

/**
 * This is a general exception for user favor errors.
 */
public class UserFavorException extends RuntimeException {
    /**
     * Create a new user favor exception.
     *
     * @param message the error message
     */
    public UserFavorException(String message) {
        super(message);
    }
}
