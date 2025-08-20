package be.brahms.rent_serve.models.forms.favor;

import be.brahms.rent_serve.models.entities.Favor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record FavorForm to save into a Favor Entity
 *
 * @param nameFavor    The name of favor
 * @param nameCategory The name of category
 */
public record FavorForm(
        @NotBlank
        String nameFavor,
        @NotNull
        String nameCategory
) {
    /**
     * Convert this FavorForm into a Favor entity
     *
     * @return a new instance initialized with data form this form
     */
    public Favor toEntity() {
        return new Favor(
                this.nameFavor,
                this.nameCategory
        );
    }
}
