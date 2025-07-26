package be.brahms.rent_serve.models.dtos.catetory;

import be.brahms.rent_serve.models.entities.Category;

public record CategoryDto(
        long id,
        String nameCategory
) {
    public static CategoryDto fromEntity(Category category) {
        return new CategoryDto(category.getId(), category.getNameCategory());
    }
}
