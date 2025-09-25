package be.brahms.rent_serve.models.forms.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Record UserMaterialForm into a UserMaterial entity
 *
 * @param stateMaterial       The state of material from user
 * @param descriptionMaterial Text to describe the material
 * @param priceHourMaterial   the price of material per hour
 * @param isAvailable         the availability of the material
 * @param materialId          the ID of material
 * @param pictureNames        the string of picture
 */
public record UserMaterialCreateForm(
        @NotNull
        State stateMaterial,
        @NotBlank
        String descriptionMaterial,
        @Digits(integer = 4, fraction = 2) // 4 numbers before the dot and 2 after de dot
        @DecimalMin(value = "0.5", inclusive = false) // The value can be start 0.5 or more but never under
        BigDecimal priceHourMaterial,
        @NotNull
        Boolean isAvailable,
        @NotNull
        Long materialId,
        List<String> pictureNames
) {
    /**
     * Converts this form into a UserMaterial object.
     *
     * @return A new UserMaterial with the data from the form.
     */
    public UserMaterial toEntity() {
        UserMaterial newUserMaterial = new UserMaterial(); // Create a new object
        // Set the state, description, price, and availability
        newUserMaterial.setStateMaterial(stateMaterial);
        newUserMaterial.setDescriptionMaterial(descriptionMaterial);
        newUserMaterial.setPriceHourMaterial(priceHourMaterial);
        newUserMaterial.setAvailable(isAvailable);

        // If materialId is not null, create a Material and set its ID
        if (this.materialId != null) {
            Material material = new Material();
            material.setId(this.materialId);
            newUserMaterial.setMaterial(material);
        }

        // If there are picture names, create Picture objects and add them
        if (pictureNames != null && !pictureNames.isEmpty()) {
            // Go through each picture name to create a set of Picture objects
            Set<Picture> pictures = pictureNames.stream()
                    .map(name -> {
                        Picture picture = new Picture(); // Create a new Picture
                        picture.setNamePicture(name); // Set a name of this picture
                        return picture;
                    }).collect(Collectors.toSet()); // Collect all pictures in a set

            newUserMaterial.setPictures(pictures); // Add pictures to UserMaterial
        }

        // Return the final UserMaterial object
        return newUserMaterial;
    }
}
