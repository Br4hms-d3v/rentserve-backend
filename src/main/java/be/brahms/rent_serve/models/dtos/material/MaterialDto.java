package be.brahms.rent_serve.models.dtos.material;

import be.brahms.rent_serve.enums.State;
import be.brahms.rent_serve.models.entities.Material;

/**
 * Data Transfer Object (DTO) for a material
 * This class is used to transfer material data
 *
 * @param id                  the unique identifier for material
 * @param nameMaterial        the name of material
 * @param descriptionMaterial the description of material
 * @param priceHourMaterial   the price per hours for the material
 * @param stateMaterial       the state of material
 */
public record MaterialDto(
        long id,
        String nameMaterial,
        String descriptionMaterial,
        Double priceHourMaterial,
        State stateMaterial
) {
    /**
     * Convert from entity to a DTO
     *
     * @param material the entity material convert
     * @return a new MaterialDto with data from the material entity
     */
    public static MaterialDto fromEntity(Material material) {
        return new MaterialDto(material.getId(), material.getNameMaterial(), material.getDescriptionMaterial(), material.getPriceHourMaterial().doubleValue(), material.getStateMaterial());
    }
}
