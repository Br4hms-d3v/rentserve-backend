package be.brahms.rent_serve.models.forms.favor;

import be.brahms.rent_serve.models.entities.Favor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Record FavorForm to save into a Favor Entity
 *
 * @param nameFavor    The name of favor
 * @param category The name of category
 * @param isAvailable The boolean truth is available for user and false it's not display for user
 */
public record FavorForm(
        @NotBlank
        String nameFavor,
        @NotNull
        String category,
        @NotNull
        Boolean isAvailable
) {
    /**
     * Convert this FavorForm into a Favor entity
     *
     * @return a new instance initialized with data form this form
     */
    public Favor toEntity() {
        return new Favor(
                this.nameFavor,
                this.category,
                this.isAvailable
        );
    }
}
