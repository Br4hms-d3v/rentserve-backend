package be.brahms.rent_serve.exceptions.category;

/**
 * Exception evoked when list of categories are empty
 */
public class CategorieNotFoundException extends CategoryException {
    /**
     * Make a new exception when the category doesn't exist.
     *
     * @param message the error message
     */
    public CategorieNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a Categories are not found
     */
    public CategorieNotFoundException() {
        super("La catégorie est introuvable.");
    }
}
