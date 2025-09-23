package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.math.BigDecimal;

public record UserMaterialDto(
        Long id,
        State stateMaterial,
        BigDecimal priceHourMaterial,
        Boolean isAvailable,
        String picture
) {
    public static UserMaterialDto fromEntity(UserMaterial userMaterial) {
        String firstPicture = userMaterial.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .findFirst()
                .orElse(null);

        return new UserMaterialDto(
                userMaterial.getId(),
                userMaterial.getStateMaterial(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                firstPicture
        );
    }
}
