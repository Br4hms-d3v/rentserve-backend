package be.brahms.rent_serve.exceptions.userMaterial;

/**
 * This is a general class for exception UserMaterial errors
 */
public class UserMaterialException extends RuntimeException {

    /**
     * Create a new user material exception
     *
     * @param message the error message
     */
    public UserMaterialException(String message) {
        super(message);
    }
}
