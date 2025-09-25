package be.brahms.rent_serve.exceptions.userMaterial;

/**
 * Exception evoked when user material doesn't found
 */
public class UserMaterialNotFoundException extends UserMaterialException {

    /**
     * Make a new exception when the user material doesn't exist.
     *
     * @param message the error message
     */
    public UserMaterialNotFoundException(String message) {
        super(message);
    }

    /**
     * This exception is used when a user material is not found
     */
    public UserMaterialNotFoundException() {
        super("Le material n'existe pas");
    }
}
