package be.brahms.rent_serve.exceptions.favor;

/**
 * This is a general class for exception Favor errors
 */
public class FavorException extends RuntimeException {
    /**
     * Create a new favor exception
     *
     * @param message the error message
     */
    public FavorException(String message) {
        super(message);
    }
}
