package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.math.BigDecimal;
import java.util.List;

public record UserMaterialByIdDto(
        long id,
        String descriptionMaterial,
        State stateMaterial,
        BigDecimal priceHourMaterial,
        Boolean isAvailable,
        List<String> pictures,
        UserDto user,
        MaterialDto material
) {
    public static UserMaterialByIdDto fromEntity(UserMaterial userMaterial) {
        List<String> allPictures = userMaterial.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .toList();

        return new UserMaterialByIdDto(
                userMaterial.getId(),
                userMaterial.getDescriptionMaterial(),
                userMaterial.getStateMaterial(),
                userMaterial.getPriceHourMaterial(),
                userMaterial.isAvailable(),
                allPictures,
                UserDto.fromEntity(userMaterial.getUser()),
                MaterialDto.fromEntity(userMaterial.getMaterial())
        );
    }

}
