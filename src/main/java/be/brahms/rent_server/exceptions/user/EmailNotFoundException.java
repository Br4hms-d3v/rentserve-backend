package be.brahms.rent_server.exceptions.user;

/**
 * Exception evoked when the email from the user doesn't exist.
 */
public class EmailNotFoundException extends RuntimeException {

    /**
     * Make a new exception when the email's user doesn't exist.
     *
     * @param message the error message
     */
    public EmailNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when an email doesn't exist.
     */
    public EmailNotFoundException() {
        super("L'email n'existe pas.");
    }
}
