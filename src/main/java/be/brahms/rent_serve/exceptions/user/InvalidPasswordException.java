package be.brahms.rent_serve.exceptions.user;

/**
 * This exception is thrown when a password is invalid.
 * It is a type of {@link UserException} used to indicate that
 * an operation requiring a password has failed because the password is incorrect.
 * <p>
 * Example:
 * <pre>
 * if (!isPasswordValid(password)) {
 *     throw new InvalidPasswordException("The password is incorrect!");
 * }
 * </pre>
 *
 * @see UserException
 */
public class InvalidPasswordException extends UserException {

    /**
     * Creates a new InvalidPasswordException.
     *
     * @param message The error message that explains why the exception occurred.
     */
    public InvalidPasswordException(String message) {
        super(message);
    }
}
