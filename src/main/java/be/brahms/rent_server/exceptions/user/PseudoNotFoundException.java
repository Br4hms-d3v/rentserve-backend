package be.brahms.rent_server.exceptions.user;

/**
 * Exception evoked when the pseudo from user doesn't exist.
 */
public class PseudoNotFoundException extends UserException {

    /**
     * Make a new exception when the pseudo doesn't exist.
     *
     * @param message the error message
     */
    public PseudoNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a pseudo is not found.
     */
    public PseudoNotFoundException() {
        super("Le pseudo n'existe pas");
    }
}
