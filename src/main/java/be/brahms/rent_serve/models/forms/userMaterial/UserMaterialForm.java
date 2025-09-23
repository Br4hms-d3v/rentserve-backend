package be.brahms.rent_serve.models.forms.userMaterial;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.UserMaterial;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record UserMaterialForm(
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
        Long materialId
) {
    public UserMaterial toEntity() {
//        return new UserMaterial(this.stateMaterial, this.descriptionMaterial, this.priceHourMaterial, this.isAvailable);

        UserMaterial userMaterial = new UserMaterial();
        userMaterial.setStateMaterial(stateMaterial);
        userMaterial.setDescriptionMaterial(descriptionMaterial);
        userMaterial.setPriceHourMaterial(priceHourMaterial);
        userMaterial.setAvailable(isAvailable);

        if(this.materialId != null) {
            Material material = new Material();
            material.setId(this.materialId);
            userMaterial.setMaterial(material);
        }

        return userMaterial;

    }

}
