package be.brahms.rent_serve.exceptions.userFavor;

public class UserFavourEmptyException extends UserFavorException {
    public UserFavourEmptyException(String message) {
        super(message);

    }

    public UserFavourEmptyException() {
        super("la liste est vide");
    }
}
