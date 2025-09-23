package be.brahms.rent_serve.exceptions.userMaterial;

public class UserMaterialNotFoundException extends UserMaterialException {

    public UserMaterialNotFoundException(String message) {
        super(message);
    }

    public UserMaterialNotFoundException() {
        super("Le material n'existe pas");
    }
}
