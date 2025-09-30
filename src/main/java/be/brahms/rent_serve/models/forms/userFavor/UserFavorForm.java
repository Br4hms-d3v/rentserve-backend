package be.brahms.rent_serve.models.forms.userFavor;

import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.entities.UserFavor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Record UserFavorForm into a UserFavor entity
 *
 * @param descriptionFavor The text to describe the favor
 * @param priceHourFavor   The price per hour
 * @param isAvailable      The availability of favor
 * @param favorId          The ID of favor
 */
public record UserFavorForm(
        @NotBlank
        String descriptionFavor,
        @Digits(integer = 4, fraction = 2) // 4 numbers before the dot and 2 after de dot
        @DecimalMin(value = "5.0", inclusive = false) // The value can be start 5.0 or more but never under
        BigDecimal priceHourFavor,
        @NotNull
        Boolean isAvailable,
        @NotNull
        Long favorId
) {
    /**
     * Convert this form into a UserFavor entity
     *
     * @return a new UserFavor with the data from this form
     */
    public UserFavor toEntity() {
        UserFavor userFavor = new UserFavor();

        userFavor.setDescriptionFavor(descriptionFavor);
        userFavor.setPriceHourFavor(priceHourFavor);
        userFavor.setAvailable(isAvailable);

        if (this.favorId != null) {
            Favor favor = new Favor();
            favor.setId(favorId);
            userFavor.setFavor(favor);
        }

        return userFavor;
    }
}
