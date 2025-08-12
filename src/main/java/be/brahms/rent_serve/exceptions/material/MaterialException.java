package be.brahms.rent_serve.exceptions.material;

/**
 * This is a general class exception for material errors.
 */
public class MaterialException extends RuntimeException {
    /**
     * Create a new material exception
     *
     * @param message the error message
     */
    public MaterialException(String message) {
        super(message);
    }
}
