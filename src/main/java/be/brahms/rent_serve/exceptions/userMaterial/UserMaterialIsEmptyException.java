package be.brahms.rent_serve.exceptions.userMaterial;

public class UserMaterialIsEmptyException extends UserMaterialException {

    public UserMaterialIsEmptyException(String message) {
        super(message);
    }

    public UserMaterialIsEmptyException() {
        super("La liste est vide!");
    }

}
