package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object (DTO) for a UserMaterial By ID
 *
 * @param id                  The unique identifier
 * @param descriptionMaterial The text to describe the material
 * @param stateMaterial       The state of material Good or Bad
 * @param priceHourMaterial   The price to rent per hour
 * @param isAvailable         The available material true or false
 * @param pictures            The string for picture
 * @param user                The user
 * @param material            The material
 */
public record UserMaterialByIdDto(
        long id,
        String descriptionMaterial,
        State stateMaterial,
        BigDecimal priceHourMaterial,
        Boolean isAvailable,
        List<String> pictures,
        UserDto user,
        MaterialDto material
) {
    /**
     * Makes a UserMaterialByIdDto from a UserMaterial object.
     *
     * @param userMaterial The UserMaterial object with data.
     * @return A new UserMaterialByIdDto with information from the UserMaterial.
     */
    public static UserMaterialByIdDto fromEntity(UserMaterial userMaterial) {
        // Get a list of picture names from userMaterial
        List<String> allPictures = userMaterial.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .toList();

        // Create and return UserMaterialByIdDto with values from userMaterial and pictures
        return new UserMaterialByIdDto(
                userMaterial.getId(),
                userMaterial.getDescriptionMaterial(),
                userMaterial.getStateMaterial(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                allPictures,
                UserDto.fromEntity(userMaterial.getUser()), // Convert user information to UserDto
                MaterialDto.fromEntity(userMaterial.getMaterial()) // Convert material information to MaterialDto
        );
    }

}
