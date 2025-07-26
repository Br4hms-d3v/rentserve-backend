package be.brahms.rent_serve.exceptions.category;

/**
 * Exception evoked when a category already exist
 */
public class CategoryExistException extends CategoryException {
    /**
     * Make a new exception when the category exist.
     *
     * @param message the error message
     */
    public CategoryExistException(String message) {
        super(message);
    }

    /**
     * This exception is used when a category is already exist
     */
    public CategoryExistException() {
        super("La categorie existe déjà !");
    }


}
