package be.brahms.rent_serve.models.dtos.material;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.util.List;
import java.util.stream.Collectors;

public record MaterialDto(
        long id,
        String nameMaterial,
        String picture
) {

    // Convert one Material + UserMaterial into a DTO
    public static MaterialDto fromEntity(Material material, UserMaterial userMaterial) {
        String picture = userMaterial.getPictures()
                .stream()
                .findFirst()
                .map(Picture::getNamePicture)
                .orElse(null);


        return new MaterialDto(
                material.getId(),
                material.getNameMaterial(),
                picture
        );
    }

    // Convert one Material into List<MaterialDto>, one DTO per UserMaterial
    public static List<MaterialDto> listFromMaterial(Material material) {
        return material.getUserMaterials()
                .stream()
                .map(um -> fromEntity(material, um))
                .collect(Collectors.toList());
    }
}
