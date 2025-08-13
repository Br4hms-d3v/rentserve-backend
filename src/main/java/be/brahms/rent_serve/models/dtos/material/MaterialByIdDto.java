package be.brahms.rent_serve.models.dtos.material;

import be.brahms.rent_serve.models.entities.Material;

/**
 * A Data Transfer Object (DTO) for material information.
 * It contains simple data about a material and its details.
 *
 * @param id           The unique identifier
 * @param nameMaterial The name of material
 * @param isAvailable  The material is available
 * @param category     The name of category
 */
public record MaterialByIdDto(
        long id,
        String nameMaterial,
        boolean isAvailable,
        String category
) {

    /**
     * Create a MaterialDto from one Material and one UserMaterial.
     *
     * @param material     the main material object
     * @return a new MaterialDto with data from both objects
     */
    public static MaterialByIdDto fromEntity(Material material) {

        return new MaterialByIdDto(
                material.getId(),
                material.getNameMaterial(),
                material.isAvailable(),
                material.getCategory().getNameCategory()
        );
    }

}
