package be.brahms.rent_serve.exceptions.material;

/**
 * Exception evoked when the material is empty
 */
public class MaterialNotFoundException extends MaterialException {
    /**
     * Make a new exception when the material doesn't exist.
     *
     * @param message return a message
     */
    public MaterialNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a Material is not found
     */
    public MaterialNotFoundException() {
        super("Le material n'existe pas");
    }
}
