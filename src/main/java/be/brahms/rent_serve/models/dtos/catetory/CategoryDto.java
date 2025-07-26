package be.brahms.rent_serve.models.dtos.catetory;

import be.brahms.rent_serve.models.entities.Category;

public record CategoryDto(
        String nameCategory
) {
    public static CategoryDto fromEntity(Category category) {
        return new CategoryDto(category.getNameCategory());
    }
}
