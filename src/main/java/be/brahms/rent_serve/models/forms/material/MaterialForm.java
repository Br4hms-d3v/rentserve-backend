package be.brahms.rent_serve.models.forms.material;

import be.brahms.rent_serve.models.entities.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record MaterialForm to save into a Material entity
 *
 * @param nameMaterial The name of material
 * @param category The name of category
 * @param isAvailable The boolean truth is available for user and false it's not display for user
 */
public record MaterialForm(
        @NotBlank
        String nameMaterial,
        @NotNull
        String category,
        @NotNull
        Boolean isAvailable
) {
    /**
     * Convert this MaterialForm into a Material entity
     *
     * @return a new instance initialized with data form this form
     */
    public Material toEntity() {
        return new Material(this.nameMaterial, this.category, this.isAvailable);
    }
}
