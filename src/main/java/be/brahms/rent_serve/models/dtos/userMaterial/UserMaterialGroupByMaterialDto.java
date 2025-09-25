package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.util.List;

public record UserMaterialGroupByMaterialDto(
        MaterialDto material,
        List<UserMaterialDto> userMaterials
) {

    public static UserMaterialGroupByMaterialDto fromGroup(List<UserMaterial> userMaterials) {
        if (userMaterials == null || userMaterials.isEmpty()) {
            throw new IllegalArgumentException("userMaterials list is empty");
        }

        MaterialDto materialDto = MaterialDto.fromEntity(userMaterials.getFirst().getMaterial());
        List<UserMaterialDto> userMaterialsDto = userMaterials.stream()
                .map(UserMaterialDto::fromEntity)
                .toList();

        return new UserMaterialGroupByMaterialDto(materialDto, userMaterialsDto);
    }
}
