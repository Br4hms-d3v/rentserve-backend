package be.brahms.rent_serve.models.forms.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserMaterialCreateForm(
        @NotNull
        State stateMaterial,
        @NotBlank
        String descriptionMaterial,
        @Digits(integer = 4, fraction = 2) // 4 numbers before the dot and 2 after de dot
        @DecimalMin(value = "0.5", inclusive = false) // The value can be start 0.5 or more but never under
        BigDecimal priceHourMaterial,
        @NotNull
        Boolean isAvailable,
        @NotNull
        Long materialId,
        List<String> pictureNames
) {
    public UserMaterial toEntity() {
        UserMaterial newUserMaterial = new UserMaterial();
        newUserMaterial.setStateMaterial(stateMaterial);
        newUserMaterial.setDescriptionMaterial(descriptionMaterial);
        newUserMaterial.setPriceHourMaterial(priceHourMaterial);
        newUserMaterial.setAvailable(isAvailable);

        if (this.materialId != null) {
            Material material = new Material();
            material.setId(this.materialId);
            newUserMaterial.setMaterial(material);
        }

        if (pictureNames != null && !pictureNames.isEmpty()) {
            Set<Picture> pictures = pictureNames.stream()
                    .map(name -> {
                        Picture picture = new Picture();
                        picture.setNamePicture(name);
                        return picture;
                    }).collect(Collectors.toSet());

            newUserMaterial.setPictures(pictures);
        }

        return newUserMaterial;
    }
}
