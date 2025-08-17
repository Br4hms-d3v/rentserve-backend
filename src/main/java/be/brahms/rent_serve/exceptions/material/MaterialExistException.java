package be.brahms.rent_serve.exceptions.material;

/**
 * Exception evoked when the material exist already
 */
public class MaterialExistException extends MaterialException {

    /**
     * Make a new exception when the material exist.
     *
     * @param message return a message
     */
    public MaterialExistException(String message) {
        super(message);
    }

    /**
     * This exception is used when a materials are not found
     */
    public MaterialExistException() {
        super("Le material existe déjà dans notre application");
    }
}
