package be.brahms.rent_serve.models.forms.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.UserMaterial;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * A form to get data to create or update a UserMaterial.
 *
 * @param stateMaterial       The condition of the material (not null).
 * @param descriptionMaterial A short text about the material (not blank).
 * @param priceHourMaterial   The price per hour (must be bigger than 0.5, max 4 digits before dot and 2 after).
 * @param isAvailable         True if the material is available, false if not (not null).
 * @param materialId          The ID of the linked material (not null).
 */
public record UserMaterialForm(
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
        Long materialId
) {
    /**
     * Converts this form to a UserMaterial object.
     *
     * @return A new UserMaterial with the data from this form.
     */
    public UserMaterial toEntity() {
//        return new UserMaterial(this.stateMaterial, this.descriptionMaterial, this.priceHourMaterial, this.isAvailable);

        UserMaterial userMaterial = new UserMaterial();
        userMaterial.setStateMaterial(stateMaterial);
        userMaterial.setDescriptionMaterial(descriptionMaterial);
        userMaterial.setPriceHourMaterial(priceHourMaterial);
        userMaterial.setAvailable(isAvailable);

        // If materialId is set, create a Material with this ID and set it
        if (this.materialId != null) {
            Material material = new Material();
            material.setId(this.materialId); // Set the ID of the material to the value from this form
            userMaterial.setMaterial(material); // Link the material object to the userMaterial
        }

        // Return the new UserMaterial object
        return userMaterial;

    }

}
