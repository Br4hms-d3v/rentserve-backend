package be.brahms.rent_serve.exceptions.favor;

/**
 * Exception evoked when favor is not found
 */
public class FavorNotFoundException extends FavorException {

    /**
     * Make a new exception when the favor doesn't found
     *
     * @param message the error message
     */
    public FavorNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a favor is not found
     */
    public FavorNotFoundException() {
        super("Le service n'a pas été retrouvé");
    }
}
