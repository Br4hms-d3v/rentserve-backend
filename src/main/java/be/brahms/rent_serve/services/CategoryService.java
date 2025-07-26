package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.Category;

import java.util.List;

/**
 * Service interface for managing category.
 * Defines business operations related to Category entities.
 */
public interface CategoryService {

    /**
     * This method get a list of categories
     *
     * @return list of all categories
     */
    List<Category> findAllCategories();
}
