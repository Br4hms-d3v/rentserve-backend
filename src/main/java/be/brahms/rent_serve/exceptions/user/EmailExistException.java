package be.brahms.rent_serve.exceptions.user;

/**
 * Exception evoked when the email address of a user already exists.
 */
public class EmailExistException extends UserException {

    /**
     * Make a new exception when the email already exists.
     *
     * @param message the error message
     */
    public EmailExistException(String message) {
        super(message);
    }

    /**
     * This exception is used when an email already exists.
     */
    public EmailExistException() {
        super("Cette adresse e-mail existe déjà!");
    }
}
