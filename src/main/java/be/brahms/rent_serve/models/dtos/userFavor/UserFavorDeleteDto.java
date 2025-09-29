package be.brahms.rent_serve.models.dtos.userFavor;

public record UserFavorDeleteDto(
        String message
) {
    public static UserFavorDeleteDto successDeleting() {
        return new UserFavorDeleteDto("Le service a bien été supprimé");
    }
}
