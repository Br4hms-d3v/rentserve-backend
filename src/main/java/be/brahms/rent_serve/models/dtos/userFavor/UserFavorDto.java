package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.math.BigDecimal;

public record UserFavorDto(
        Long id,
        BigDecimal priceHourFavor,
        Boolean isAvailable,
        String picture
) {
    public static UserFavorDto fromEntity(UserFavor userFavor) {
        String firstPicture = userFavor.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .findFirst()
                .orElse("Aucune photo trouvée");

        return new UserFavorDto(
                userFavor.getId(),
                userFavor.getPriceHourFavor(),
                userFavor.isAvailable(),
                firstPicture
        );
    }
}
