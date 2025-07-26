package be.brahms.rent_serve.exceptions.category;

/**
 * This is a general exception for category errors.
 */
public class CategoryException extends RuntimeException {

    /**
     * Create a new category exception.
     *
     * @param message the error message
     */
    public CategoryException(String message) {
        super(message);
    }
}
