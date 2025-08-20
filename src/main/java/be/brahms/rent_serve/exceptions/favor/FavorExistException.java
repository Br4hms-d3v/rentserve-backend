package be.brahms.rent_serve.exceptions.favor;

/**
 * Exception evoked when favor is already exist
 */
public class FavorExistException extends FavorException {
    /**
     * Make a new exception when the favor doesn't found
     *
     * @param message the error message
     */
    public FavorExistException(String message) {
        super(message);
    }

    /**
     * This exception is used when favor is already exist
     */
    public FavorExistException() {
        super("Le service existe déjà");
    }
}
