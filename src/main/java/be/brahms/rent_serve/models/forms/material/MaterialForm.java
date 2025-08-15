package be.brahms.rent_serve.models.forms.material;

import be.brahms.rent_serve.models.entities.Material;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record MaterialForm to save into a Material entity
 *
 * @param nameMaterial The name of material
 */
public record MaterialForm(
        @NotBlank
        String nameMaterial,
        @NotNull
        String nameCategory
) {
    /**
     * Convert this MaterialForm into a Material entity
     *
     * @return a new instance initialized with data form this form
     */
    public Material toEntity() {
        return new Material(this.nameMaterial, this.nameCategory);
    }
}
