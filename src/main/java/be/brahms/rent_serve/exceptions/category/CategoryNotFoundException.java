package be.brahms.rent_serve.exceptions.category;

/**
 * Exception evoked when list of categories are empty
 */
public class CategoryNotFoundException extends CategoryException {
    /**
     * Make a new exception when the category doesn't exist.
     *
     * @param message the error message
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a Categories are not found
     */
    public CategoryNotFoundException() {
        super("La catégorie est introuvable.");
    }
}
