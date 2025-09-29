package be.brahms.rent_serve.models.forms.userFavor;

import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserFavorNewForm(
        @NotBlank
        String descriptionFavor,
        @Digits(integer = 4, fraction = 2) // 4 numbers before the dot and 2 after de dot
        @DecimalMin(value = "5.0", inclusive = false) // The value can be start 5.0 or more but never under
        BigDecimal priceHourFavor,
        @NotNull
        Boolean isAvailable,
        @NotNull
        Long favorId,
        List<String> pictureNames
) {
    public UserFavor toEntity() {
        UserFavor newUserFavor = new UserFavor();

        newUserFavor.setDescriptionFavor(descriptionFavor);
        newUserFavor.setPriceHourFavor(priceHourFavor);
        newUserFavor.setAvailable(isAvailable);

        if (this.favorId != null) {
            Favor favor = new Favor();
            favor.setId(this.favorId);
            newUserFavor.setFavor(favor);
        }

        if (this.pictureNames != null && !this.pictureNames.isEmpty()) {
            Set<Picture> pictures = pictureNames
                    .stream()
                    .map(name -> {
                        Picture picture = new Picture();
                        picture.setNamePicture(name);
                        return picture;
                    })
                    .collect(Collectors.toSet());

            newUserFavor.setPictures(pictures);
        }

        return newUserFavor;

    }
}
