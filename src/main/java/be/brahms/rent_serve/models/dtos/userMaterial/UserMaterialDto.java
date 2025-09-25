package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.math.BigDecimal;

/**
 * A DTO for UserMaterial for simple information
 *
 * @param id                The identifier of user material
 * @param stateMaterial     The state of material
 * @param priceHourMaterial the price per hour for a material
 * @param isAvailable       the availability of material
 * @param picture           the picture of material
 */
public record UserMaterialDto(
        Long id,
        State stateMaterial,
        BigDecimal priceHourMaterial,
        Boolean isAvailable,
        String picture
) {
    /**
     * Creates a UserMaterialDto from a UserMaterial object.
     * It uses only the first picture from the list (if there is one).
     *
     * @param userMaterial The UserMaterial object with data.
     * @return A new UserMaterialDto with selected information.
     */
    public static UserMaterialDto fromEntity(UserMaterial userMaterial) {
        // Get the first picture name, or null if there is no picture
        String firstPicture = userMaterial.getPictures()
                .stream()// Go through the list of pictures
                .map(Picture::getNamePicture) // Get the name of each picture
                .findFirst() // Take the first one
                .orElse(null); // If no picture return null

        // Create and return the UserMaterialDto with values from userMaterial
        return new UserMaterialDto(
                userMaterial.getId(),
                userMaterial.getStateMaterial(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                firstPicture
        );
    }
}
