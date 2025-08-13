package be.brahms.rent_serve.models.dtos.material;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Data Transfer Object (DTO) for material information.
 * It contains simple data about a material and its details.
 *
 * @param id                  The unique identifier
 * @param nameMaterial        The name of material
 * @param descriptionMaterial The description explain detail of material
 * @param priceMaterial       The price per hour
 * @param stateMaterial       The state of material
 * @param picture             The picture to show the material
 */
public record MaterialDto(
        long id,
        String nameMaterial,
        String descriptionMaterial,
        double priceMaterial,
        State stateMaterial,
        String picture
) {

    /**
     * Create a MaterialDto from one Material and one UserMaterial.
     *
     * @param material     the main material object
     * @param userMaterial the user-specific material details
     * @return a new MaterialDto with data from both objects
     */
    public static MaterialDto fromEntity(Material material, UserMaterial userMaterial) {

        String descriptionMaterial = userMaterial.getDescriptionMaterial();
        double priceMaterial = userMaterial.getPriceHourMaterial().doubleValue();
        State stateMaterial = userMaterial.getStateMaterial();

        // Display only one picture
        String picture = userMaterial.getPictures()
                .stream()
                .findFirst()
                .map(Picture::getNamePicture)
                .orElse(null);


        return new MaterialDto(
                material.getId(),
                material.getNameMaterial(),
                descriptionMaterial,
                priceMaterial,
                stateMaterial,
                picture
        );
    }

    /**
     * Create a list of MaterialDto from one Material.
     * One MaterialDto is created for each UserMaterial inside the Material.
     *
     * @param material the main material object
     * @return a list of MaterialDto, one for each UserMaterial
     */
    public static List<MaterialDto> listFromMaterial(Material material) {
        return material.getUserMaterials()
                .stream()
                .map(um -> fromEntity(material, um))
                .collect(Collectors.toList());
    }
}
