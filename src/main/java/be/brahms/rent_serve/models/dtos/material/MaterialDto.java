package be.brahms.rent_serve.models.dtos.material;

import be.brahms.rent_serve.models.entities.Material;

/**
 * A Data Transfer Object (DTO) for material information.
 * It contains simple data about a material and its details.
 *
 * @param id           The unique identifier
 * @param nameMaterial The name of material
 */
public record MaterialDto(
        long id,
        String nameMaterial
) {

    /**
     * Create a MaterialDto from one Material and one UserMaterial.
     *
     * @param material the main material object
     * @return a new MaterialDto with data from both objects
     */
    public static MaterialDto fromEntity(Material material) {

        return new MaterialDto(
                material.getId(),
                material.getNameMaterial());
    }

}
