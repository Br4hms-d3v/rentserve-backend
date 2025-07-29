package be.brahms.rent_serve.models.dtos.catetory;

import be.brahms.rent_serve.models.entities.Category;

/**
 * Data Transfert Object (DTO) for a Category
 * This class is used to transfer category data (id and name) between layers
 * @param id the unique identifier of teh category
 * @param nameCategory the name of the category
 */
public record CategoryDto(
        long id,
        String nameCategory
) {
    /**
     * Convert a link entity to a link Dto
     * @param category the entity to convert
     * @return a enw CategoryDto with data from the given category
     */
    public static CategoryDto fromEntity(Category category) {
        return new CategoryDto(category.getId(), category.getNameCategory());
    }
}
