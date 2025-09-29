package be.brahms.rent_serve.models.forms.userFavor;

import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.entities.UserFavor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

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
