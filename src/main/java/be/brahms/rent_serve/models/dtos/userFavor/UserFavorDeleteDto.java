package be.brahms.rent_serve.models.dtos.userFavor;

/**
 * Data Transfer Object for a UserFavor delete
 *
 * @param message The message of the response
 */
public record UserFavorDeleteDto(
        String message
) {
    /**
     * Create a UserFavorDeleteDto with a success message
     *
     * @return A new UserFavorDeleteDto with a success message
     */
    public static UserFavorDeleteDto successDeleting() {
        return new UserFavorDeleteDto("Le service a bien été supprimé");
    }
}
