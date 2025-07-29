package be.brahms.rent_serve.models.forms.category;

import be.brahms.rent_serve.models.entities.Category;
import jakarta.validation.constraints.NotBlank;

/**
 * Record CategoryForm into a Category entity
 *
 * @param nameCategory the name of category
 */
public record CategoryForm(
        @NotBlank
        String nameCategory
) {
    /**
     * Converts this UserForm into a User entity
     *
     * @return a new instance initialized with data from this form
     */
    public Category toEntity() {
        return new Category(this.nameCategory);
    }
}
