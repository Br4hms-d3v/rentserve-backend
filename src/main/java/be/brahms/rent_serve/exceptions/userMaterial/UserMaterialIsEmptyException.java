package be.brahms.rent_serve.exceptions.userMaterial;

/**
 * Exception evoked when user material is empty
 */
public class UserMaterialIsEmptyException extends UserMaterialException {

    /**
     * Make a new exception when the user material is empty.
     *
     * @param message The error message to show
     */
    public UserMaterialIsEmptyException(String message) {
        super(message);
    }

    /**
     * This exception is used when a user material is empty
     */
    public UserMaterialIsEmptyException() {
        super("La liste est vide!");
    }

}
