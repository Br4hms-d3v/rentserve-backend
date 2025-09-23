package be.brahms.rent_serve.models.dtos.userMaterial;


public record UserMaterialDeleteDto(
        String message
) {
    public static UserMaterialDeleteDto successDeleting() {
        return new UserMaterialDeleteDto("Le matériel a bien été supprimé");
    }
}
