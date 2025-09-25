package be.brahms.rent_serve.models.dtos.userMaterial;

/**
 * A DTO that gives a message after deleting a user material.
 *
 * @param message A text message (for example: "User material deleted successfully").
 */
public record UserMaterialDeleteDto(
        String message
) {
    /**
     * Creates a message to say the user material was deleted successfully.
     *
     * @return A UserMaterialDeleteDto with a success message.
     */
    public static UserMaterialDeleteDto successDeleting() {
        return new UserMaterialDeleteDto("Le matériel a bien été supprimé");
    }
}
